package uz.vianet.mvctesting

import org.junit.Test
import org.junit.Assert.*
import uz.vianet.mvctesting.network.RetrofitHttp

class UnitTestMainAct {

    // Tests for load all posts

    @Test
    fun checkStatusCode() {
        val response = RetrofitHttp.postService.listPost().execute()
        assertEquals(response.code(),200)
    }

    @Test
    fun responseIsSuccessful() {
        val response = RetrofitHttp.postService.listPost().execute()
        assertTrue(response.isSuccessful)
    }

    @Test
    fun checkPostListNotNull() {
        val response = RetrofitHttp.postService.listPost().execute()
        assertNotNull(response.body())
    }

    @Test
    fun checkPostListSize() {
        val response = RetrofitHttp.postService.listPost().execute()
        var posts = response.body()
        assertEquals(posts!!.size, 100)
    }

    @Test
    fun checkFirstUserId() {
        val response = RetrofitHttp.postService.listPost().execute()
        var posts = response.body()
        val post = posts!![0]
        assertEquals(post.userId, 1)
    }
    // Tests for delete post response body pustoy keladi
    @Test
    fun checkStatusCodeDeletePost() {
        val response = RetrofitHttp.postService.deletePost(2).execute()
        assertEquals(response.code(),200)
    }
    @Test
    fun checkDelPostistNotNull() {
        val response = RetrofitHttp.postService.deletePost(2).execute()
        assertNotNull(response.body())
    }

    @Test
    fun responseDelPostIsSuccessful() {
        val response = RetrofitHttp.postService.deletePost(2).execute()
        assertTrue(response.isSuccessful)
    }
}