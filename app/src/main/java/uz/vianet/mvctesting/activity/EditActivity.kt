package uz.vianet.mvctesting.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.mvctesting.databinding.ActivityEditBinding
import uz.vianet.mvctesting.model.Post
import uz.vianet.mvctesting.network.RetrofitHttp

class EditActivity : AppCompatActivity() {

    lateinit var id: String
    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }
    fun initViews(){
        val extras = intent.extras

        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            binding.etUserId.setText(extras.getString("user_id"))
            binding.etTitle.setText(extras.getString("title"))
            binding.etText.setText(extras.getString("body"))
            id = extras.getString("id")!!
        }
        binding.btnSubmit.setOnClickListener { editPost() }
    }
    fun editPost() {
        val title = binding.etTitle.text.toString()
        val body = binding.etText.text.toString().trim { it <= ' ' }
        val id_user = binding.etUserId.text.toString().trim { it <= ' ' }
        val id = id
        val post = Post(id.toInt(),id_user.toInt(), title, body)
        apiPostUpdate(post)
    }
    fun apiPostUpdate(post: Post) {
        RetrofitHttp.postService.updatePost(post.id, post)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@EditActivity, response.body()!!.title +" Updated", Toast.LENGTH_LONG).show()
                    if (response.body() != null) {
                        Log.d("@@@", response.body().toString())
                        val intent = Intent(this@EditActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("@@@", response.toString())
                    }
                }

                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    Log.d("@@@", t.toString())
                }
            })
    }
}