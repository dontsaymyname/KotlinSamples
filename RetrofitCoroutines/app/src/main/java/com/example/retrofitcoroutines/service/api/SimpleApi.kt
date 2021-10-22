package com.example.retrofitcoroutines.service.api

import com.example.retrofitcoroutines.service.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/1")
    suspend fun getPostWithDynHeaders(@Header("Auth") auth: String): Response<Post>

    @GET("posts/{userId}")
    suspend fun getPostByUserId(
        @Path("userId") userId: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getOrganizedPost(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>
}