package uz.vianet.mvctesting.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.mvctesting.databinding.ActivityCreateBinding
import uz.vianet.mvctesting.model.Post
import uz.vianet.mvctesting.network.RetrofitHttp
import uz.vianet.mvctesting.utils.Utils.toast

class CreateActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateBinding
    lateinit var uid:String
    lateinit var title:String
    lateinit var body:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        uid = binding.etUserId.text.toString().trim()
        title = binding.etTitle.toString().trim()
        body = binding.etBody.toString().trim()

        if (uid.isNotEmpty()){
            Log.d("@@@","UID Is not empty")
        }else{
            Log.d("@@@","UID Is empty")
        }

        binding.etBody.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSubmitCreate.isEnabled = s!!.isNotEmpty()
            }

        })
        binding.btnSubmitCreate.setOnClickListener{newPost()}
    }

    private fun newPost() {
        if (title.isNotEmpty() && body.isNotEmpty() && uid.isNotEmpty()){
            val post = Post(uid.toInt(), title, body)
            apiPostCreate(post)
        }
    }

    private fun apiPostCreate(post: Post) {
        RetrofitHttp.postService.createPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.d("@@@", response.body().toString())
                Toast.makeText(this@CreateActivity, "New Post Created", Toast.LENGTH_LONG).show()
                val intent = Intent()
                setResult(RESULT_OK, intent)
                super@CreateActivity.onBackPressed()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("@@@", t.toString())
            }
        })
    }
}