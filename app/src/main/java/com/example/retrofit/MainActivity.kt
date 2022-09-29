package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.PostItem
import com.example.retrofit.networking.NetworkApi
import com.example.retrofit.networking.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val itemAdapter by lazy { ItemAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.recyclerview.adapter = itemAdapter
        val retrofit = RetrofitHelper.getRetrofit()
        val call = retrofit.create(NetworkApi::class.java)
        yukla(call)



        binding.refreshLayout.setOnRefreshListener {
            yukla(call)
        }
    }

    fun yukla(call: NetworkApi) {

        call.getPost().enqueue(object : Callback<List<PostItem>> {
            override fun onResponse(
                call: Call<List<PostItem>>,
                response: Response<List<PostItem>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    itemAdapter.submitList(data)
                    binding.refreshLayout.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<List<PostItem>>, t: Throwable) {

            }

        })

    }
}
