package iut.fauryollivier.snoozespot.app.pages.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.repositories.PostsRepository
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import iut.fauryollivier.snoozespot.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    data class ScreenState(
        val posts: List<PostDTO> = emptyList(),
        val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val error: ErrorMessage? = null,
        val page: Int = -1,
        val endReached: Boolean = false
    )

    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    private fun loadingState() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun refreshingState() {
        _state.update { it.copy(isRefreshing = true, error = null) }
    }

    private fun successState(newPostDTOList: List<PostDTO>, newPage: Int) {
        _state.update {
            it.copy(
                isLoading = false,
                isRefreshing = false,
                error = null,
                posts = it.posts + newPostDTOList,
                page = newPage,
                endReached = newPostDTOList.isEmpty()
            )
        }
    }

    private fun errorState(errorMessage: ErrorMessage = ErrorMessage.LOADING_ERROR) {
        _state.update {
            it.copy(
                isLoading = false,
                error = errorMessage
            )
        }
    }

    fun loadNextPosts() {
        if (_state.value.isLoading || _state.value.endReached) return

        loadingState()

        viewModelScope.launch {
            try {
                val nextPage = _state.value.page + 1
                val response = PostsRepository.getPosts(page = nextPage)

                if (response.isSuccessful && response.body() != null) {
                    successState(response.body()!!, nextPage)
                } else {
                    Toaster.instance.toast(R.string.failed_to_fetch_data)
                }
            } catch (_: Exception) {
                errorState()
            }
        }
    }

    fun refresh() {
        refreshingState()

        _state.update {
            it.copy(
                posts = emptyList(),
                page = -1,
                error = null,
                endReached = false
            )
        }

        viewModelScope.launch {
            try {
                val response = PostsRepository.getPosts(page = 0)
                if (response.isSuccessful && response.body() != null) {
                    successState(response.body()!!, 0)
                } else {
                    errorState()
                }
            } catch (_: Exception) {
                errorState()
            }
        }
    }

    fun newPost(navigator: DestinationsNavigator, context: Context, data: NewPostResult) {
        viewModelScope.launch {
            try {
                val response = PostsRepository.createPost(context, data.content, data.uris)
                if (response.isSuccessful && response.body() != null) {
                    navigator.navigate(FeedDetailsScreenDestination(response.body()?.id!!))
                } else {
                    Toaster.instance.toast(R.string.failed_to_create_post)
                }
            } catch (_: Exception) {
                Toaster.instance.toast(R.string.failed_to_create_post)
            }
        }
    }

    fun likePost(post: PostDTO) {
        viewModelScope.launch {
            val result = PostsRepository.likePost(post.id)
            if (result.isSuccessful && result.body() != null) {
                val liked = result.body()!!
                _state.update { current ->
                    current.copy(
                        posts = current.posts.map { p ->
                            if (p.id == post.id) {
                                p.copy(
                                    likedByUser = liked,
                                    likeCount = p.likeCount + (if (liked) 1 else -1)
                                )
                            } else p
                        }
                    )
                }
            } else {
                Toaster.instance.toast(R.string.failed_to_like_post)
            }
        }
    }

    fun deletePost(post: PostDTO) {
        viewModelScope.launch {
            val result = PostsRepository.deletePost(post.id)
            if (result.isSuccessful) {
                _state.update { current ->
                    current.copy(
                        posts = current.posts.filter { p->
                            p.id != post.id
                        }
                    )
                }
            } else {
                Toaster.instance.toast(R.string.failed_to_delete_post)
            }
        }
    }

}