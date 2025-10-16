package iut.fauryollivier.snoozespot.app.pages.feed.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.data.repositories.PostsRepository
import iut.fauryollivier.snoozespot.api.generated.model.Post
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedDetailsViewModel : ViewModel() {

    private val postState: MutableStateFlow<Post?> = MutableStateFlow(null)
    val post: StateFlow<Post?> = postState.asStateFlow()
    private var errorMessageState = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = errorMessageState.asStateFlow()

    fun fetchPost(id: Int) {
        viewModelScope.launch {
            val post = PostsRepository.getPost(id)
            if(post.isSuccessful && post.body() != null)
                postState.value = post.body()!!
            else
                errorMessageState.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

}