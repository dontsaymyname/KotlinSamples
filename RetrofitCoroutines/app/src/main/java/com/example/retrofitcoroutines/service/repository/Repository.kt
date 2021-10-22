package com.example.retrofitcoroutines.service.repository

import com.example.retrofitcoroutines.service.api.RetrofitInstance
import com.example.retrofitcoroutines.service.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPosts(): Response<List<Post>>{
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getPostWithDynHeader(auth: String): Response<Post> {
        return RetrofitInstance.api.getPostWithDynHeaders(auth)
    }

    suspend fun getPostByUserId(userId: Int): Response<Post> {
        return RetrofitInstance.api.getPostByUserId(userId)
    }

    suspend fun getCustomPost(userId: Int): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId)
    }

    suspend fun getOrganizedPost(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getOrganizedPost(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }
}