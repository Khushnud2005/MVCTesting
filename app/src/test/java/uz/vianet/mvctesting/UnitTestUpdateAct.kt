package uz.vianet.mvctesting

import org.junit.Assert
import org.junit.Test
import uz.vianet.mvctesting.model.Post
import uz.vianet.mvctesting.network.RetrofitHttp

class UnitTestUpdateAct {
    @Test
    fun checkStatusCode() {
        val post: Post = Post(25,1,"Updated Post","Post Body")
        val response = RetrofitHttp.postService.updatePost(25,post).execute()
        Assert.assertEquals(response.code(), 200)
    }

    @Test
    fun responseIsSuccessful() {
        val post: Post = Post(25,1,"Updated Post","Post Body")
        val response = RetrofitHttp.postService.updatePost(25,post).execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun checkPostTitle() {
        val post: Post = Post(25,1,"Updated Post","Post Body")
        val response = RetrofitHttp.postService.updatePost(25,post).execute()
        val title = response.body()!!.title
        Assert.assertEquals(post.title, title)
    }
    @Test
    fun checkPostBody() {
        val post: Post = Post(25,1,"Updated Post","Post Body")
        val response = RetrofitHttp.postService.updatePost(25,post).execute()
        val body = response.body()!!.body
        Assert.assertEquals(post.body, body)
    }
}