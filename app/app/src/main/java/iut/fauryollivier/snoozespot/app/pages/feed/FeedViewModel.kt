package iut.fauryollivier.snoozespot.app.pages.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.data.repositories.PostsRepository
import iut.fauryollivier.snoozespot.generated.api.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val postsState: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val posts: StateFlow<List<Post>> = postsState.asStateFlow()

    fun fetchPosts() {
        viewModelScope.launch {
            val posts = PostsRepository.getPosts()
            if(posts != null)
                postsState.value = posts
        }
    }

}