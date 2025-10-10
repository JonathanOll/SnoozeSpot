package iut.fauryollivier.snoozespot.app.pages.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.api.data.repositories.PostsRepository
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedDetailsScreenDestination
import iut.fauryollivier.snoozespot.generated.api.model.Post
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
        val error: String = "",
        val page: Int = -1,
        val endReached: Boolean = false
    )

    private val _state = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _state.asStateFlow()

    private fun loadingState() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun refreshingState() {
        _state.update { it.copy(isRefreshing = true, error = "") }
    }

    private fun successState(newPostList: List<Post>, newPage: Int) {
        _state.update {
            it.copy(
                isLoading = false,
                isRefreshing = false,
                error = "",
                posts = it.posts + newPostList,
                page = newPage,
                endReached = newPostList.isEmpty()
            )
        }
    }

    private fun errorState() {
        _state.update {
            it.copy(
                isLoading = false,
                error = "Unexpected error occured"
            )
        }
    }

    fun loadNextPosts() {
        if (_state.value.isLoading || _state.value.endReached) return

        loadingState()

        viewModelScope.launch {
            try {
                val nextPage = _state.value.page + 1
                Log.d("jonathan", "" +nextPage)
                val response = PostsRepository.getPosts(page = nextPage)

                if (response.isSuccessful && response.body() != null) {
                    successState(response.body()!!, nextPage)
                } else {
                    errorState()
                }
            } catch(e: Exception) {
                Log.e("FeedViewModel", "Error loading posts", e)
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
                error = "",
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
                Log.e("FeedViewModel", "Error refreshing posts", e)
                errorState()
            }
        }
    }

    fun newPost(navigator: DestinationsNavigator, content: String) {
        viewModelScope.launch {
            try {
                val response = PostsRepository.createPost(content)
                if(response.isSuccessful) {
                    Log.d("jonathan", response.toString())
                    navigator.navigate(FeedDetailsScreenDestination(response.body()?.id!!))
                }
            } catch(e: Exception) {
                Log.e("FeedViewModel", "Error creating post", e)

                // TODO: afficher un toast d'erreur
            }
        }
    }

}