package com.example.retrofitcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcoroutines.service.model.Post
import com.example.retrofitcoroutines.service.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myListResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponseByUser: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myOrganizedResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPosts(){
        viewModelScope.launch {
            val response = repository.getPosts()
            myListResponse.value = response
        }
    }

    fun getPostWithHeader(auth: String) {
        viewModelScope.launch {
            val response = repository.getPostWithDynHeader(auth)
            myResponse.value = response
        }
    }

    fun getPostByUserId(userId: Int) {
        viewModelScope.launch {
            val response = repository.getPostByUserId(userId)
            myResponseByUser.value = response

        }
    }

    fun getCustomPost(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCustomPost(userId)
            myCustomResponse.value = response
        }
    }

    fun getOrganizedPost(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getOrganizedPost(userId, options)
            myOrganizedResponse.value = response
        }
    }

    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse.value = response
        }
    }
}