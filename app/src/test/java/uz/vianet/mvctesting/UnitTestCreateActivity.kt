package uz.vianet.mvctesting

import org.junit.Assert
import org.junit.Test
import uz.vianet.mvctesting.model.Post
import uz.vianet.mvctesting.network.RetrofitHttp

class UnitTestCreateActivity {

    @Test
    fun responseIsSuccessful() {
        val post: Post = Post("New post","Post body",300)
        val response = RetrofitHttp.postService.createPost(post).execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun checkPostTitle() {
        val post: Post = Post("New post","Post body",300)
        val response = RetrofitHttp.postService.createPost(post).execute()
        val title = response.body()!!.title
        Assert.assertEquals(post.title, title)
    }
    @Test
    fun checkPostBody() {
        val post: Post = Post("New post","Post body",300)
        val response = RetrofitHttp.postService.createPost(post).execute()
        val body = response.body()!!.body
        Assert.assertEquals(post.body, body)
    }
}