package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.repositories.PostsRepository
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedDetailsViewModel : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _state: MutableStateFlow<PostDTO?> = MutableStateFlow(null)
    val state: StateFlow<PostDTO?> = _state.asStateFlow()

    private var _errorMessage = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = _errorMessage.asStateFlow()

    fun fetchPost(id: Int) {
        _errorMessage.update { null }

        viewModelScope.launch {
            val post = PostsRepository.getPost(id)
            if(post.isSuccessful && post.body() != null)
                _state.value = post.body()!!
            else
                _errorMessage.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

    fun likePost(id: Int) {
        viewModelScope.launch {
            val result = PostsRepository.likePost(id)
            if (result.isSuccessful) {
                val liked = result.body()!!
                _state.update { current ->
                    current?.copy(
                        likedByUser = liked,
                        likeCount = current.likeCount + (if (liked) 1 else -1)
                    )
                }
            } else {
                _events.emit(UiEvent.ShowToast(R.string.failed_to_like_post))
            }
        }
    }

    fun sendPostComment(data: NewPostResult) {
        state.value ?: return

        viewModelScope.launch {
            val result = PostsRepository.createPostComment(_state.value!!.id, data.content)
            if (!result.isSuccessful) {
                _events.emit(UiEvent.ShowToast(R.string.failed_to_comment_post))
            }
        }
    }

    fun deletePost(id: Int, navigateUp: () -> Unit) {
        viewModelScope.launch {
            val result = PostsRepository.deletePost(id)
            if (result.isSuccessful) {
                navigateUp()
            } else {
                _events.emit(UiEvent.ShowToast(R.string.failed_to_delete_post))
            }
        }
    }

    fun deleteComment(id: Int) {
        viewModelScope.launch {
            val result = PostsRepository.deletePostComment(id)
            if (result.isSuccessful) {
                _state.update { it->
                    it!!.copy(
                        comments = it.comments.filter { it.id != id }
                    )
                }
            } else {
                _events.emit(UiEvent.ShowToast(R.string.failed_to_delete_comment))
            }
        }
    }

}