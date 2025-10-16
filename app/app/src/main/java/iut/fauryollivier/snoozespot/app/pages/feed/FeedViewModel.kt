package iut.fauryollivier.snoozespot.app.pages.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.api.data.repositories.PostsRepository
import iut.fauryollivier.snoozespot.api.generated.model.Post
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedDetailsScreenDestination
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    data class ScreenState(
        val posts: List<Post> = emptyList(),
        val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val error: ErrorMessage? = null,
        val page: Int = -1,
        val endReached: Boolean = false
    )

    private val _state = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _state.asStateFlow()

    private fun loadingState() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun refreshingState() {
        _state.update { it.copy(isRefreshing = true, error = null) }
    }

    private fun successState(newPostList: List<Post>, newPage: Int) {
        _state.update {
            it.copy(
                isLoading = false,
                isRefreshing = false,
                error = null,
                posts = it.posts + newPostList,
                page = newPage,
                endReached = newPostList.isEmpty()
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
                    errorState()
                }
            } catch(e: Exception) {
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
            } catch(e: Exception) {
                errorState()
            }
        }
    }

    fun newPost(navigator: DestinationsNavigator, content: String) {
        viewModelScope.launch {
            try {
                val response = PostsRepository.createPost(content)
                if(response.isSuccessful) {
                    navigator.navigate(FeedDetailsScreenDestination(response.body()?.id!!))
                }
            } catch(e: Exception) {
                // TODO: afficher un toast d'erreur
            }
        }
    }

}