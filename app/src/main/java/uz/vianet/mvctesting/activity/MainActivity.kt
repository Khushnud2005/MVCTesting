package uz.vianet.mvctesting.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.mvctesting.R
import uz.vianet.mvctesting.adapter.PostAdapter
import uz.vianet.mvctesting.databinding.ActivityMainBinding
import uz.vianet.mvctesting.model.Post
import uz.vianet.mvctesting.network.RetrofitHttp
import uz.vianet.mvctesting.utils.Utils
import uz.vianet.mvctesting.utils.Utils.toast

class MainActivity : AppCompatActivity() {

    var posts = ArrayList<Post>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.floating.setOnClickListener { openCreateActivity() }
        apiPostList()
    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        binding.recyclerView.setAdapter(adapter)
    }
    fun openCreateActivity() {

        val intent = Intent(this@MainActivity, CreateActivity::class.java)
        launchCreateActivity.launch(intent)
    }

    var launchCreateActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            apiPostList()
        } else {
            Toast.makeText(this@MainActivity, "Operation canceled", Toast.LENGTH_LONG).show()
        }
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(this, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                apiPostDelete(post)
            }

            override fun onNegativeClick() {

            }
        })
    }

    private fun apiPostList() {
        binding.pbLoading.visibility = View.VISIBLE
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                //Log.d("@@@", response.body().toString())
                binding.pbLoading.visibility = View.GONE
                posts.clear()
                val items = response.body()
                if (items!=null){
                    for (item in items){
                        val post = Post(item.id,item.userId,item.title,item.body)
                        posts.add(post)
                    }
                }
                refreshAdapter(posts)
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.e("@@@", t.message.toString())
            }
        })
    }

    private fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                apiPostList()
                toast(this@MainActivity,"${post.title} Deleted")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }
        })
    }
}