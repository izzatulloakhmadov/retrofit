package com.example.retrofit.networking

import retrofit2.Call
import retrofit2.http.GET
import com.example.retrofit.model.PostItem

interface NetworkApi {

    @GET("posts")
    fun getPost():Call<List<PostItem>>
}