package com.example.placeholderpostview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class Post(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)

interface PostApi {

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Post

    @POST("posts")
    suspend fun createPost(@Body newPost: Post): Post

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int,
                           @Body updatedPost: Post): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int)
}


class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var postApi: PostApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.textViewResult)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        postApi = retrofit.create(PostApi::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun doIt(view: View) {
        when(view.id) {
            R.id.buttonGet -> getPost((1..100).random())
            R.id.buttonPost -> createPost(Post(userId = 1, title = "Novo Post",
                body = "Este eh um novo post"))
            R.id.buttonPut -> updatePost(1, Post(userId = 1,
                title = "Post Atualizado", body = "Este post foi atualizado"))
            R.id.buttonDelete -> deletePost(1)
        }
    }

    private fun getPost(postId: Int) {
        lifecycleScope.launch {
            try {
                val post = withContext(Dispatchers.IO) {
                    postApi.getPost(postId)
                }

                textViewResult.text = "ID ${post.id}\n\n" +
                        "Titulo: ${post.title}\n\n" +
                        "Corpo: ${post.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro!", e)
                textViewResult.text = "Erro!"
            }
        }
    }

    private fun createPost(newPost: Post) {
        lifecycleScope.launch {
            try {
                val createdPost = withContext(Dispatchers.IO) {
                    postApi.createPost(newPost)
                }

                textViewResult.text = "Post criado com sucesso\n\n" +
                        "ID: ${createdPost.id}\n\n" +
                        "Titulo: ${createdPost.title}\n\n" +
                        "Corpo: ${createdPost.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro!", e)
                textViewResult.text = "Erro!"
            }
        }
    }

    private fun updatePost(postId: Int, updatedPost: Post) {
        lifecycleScope.launch {
            try {
                val post = withContext(Dispatchers.IO) {
                    postApi.updatePost(postId, updatedPost)
                }

                textViewResult.text = "Post atualizado com sucesso\n\n" +
                        "ID: ${post.id}\n\n" +
                        "Titulo: ${post.title}\n\n" +
                        "Corpo: ${post.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro!", e)
                textViewResult.text = "Erro!"
            }
        }
    }

    private fun deletePost(postId: Int) {
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    postApi.deletePost(postId)
                }

                textViewResult.text = "Post deletado com sucesso!\n\nID: $postId"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro!", e)
                textViewResult.text = "Erro!"
            }
        }
    }
}
