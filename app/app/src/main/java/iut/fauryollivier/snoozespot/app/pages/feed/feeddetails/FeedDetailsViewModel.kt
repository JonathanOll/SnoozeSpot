package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.repositories.PostsRepository
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedDetailsViewModel : ViewModel() {

    private val postDTOState: MutableStateFlow<PostDTO?> = MutableStateFlow(null)
    val postDTO: StateFlow<PostDTO?> = postDTOState.asStateFlow()
    private var errorMessageState = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = errorMessageState.asStateFlow()

    fun fetchPost(id: Int) {
        viewModelScope.launch {
            val post = PostsRepository.getPost(id)
            if(post.isSuccessful && post.body() != null)
                postDTOState.value = post.body()!!
            else
                errorMessageState.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

    fun likePost(id: Int) {
        viewModelScope.launch {
            val result = PostsRepository.likePost(id)
            if (result.isSuccessful) {
                val liked = result.body()!!
                postDTOState.update { current ->
                    current?.copy(
                        likedByUser = liked,
                        likeCount = current.likeCount + (if (liked) 1 else -1)
                    )
                }
            } else {
                // TODO: afficher un toast d'erreur
            }
        }
    }

    fun sendPostComment(data: NewPostResult) {
        viewModelScope.launch {
            val result = PostsRepository.createPostComment(postDTOState.value!!.id, data.content)
            if (!result.isSuccessful) {
                // TODO: afficher un toast d'erreur
            }
        }
    }

}