package com.example.retrofitcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcoroutines.viewmodel.MainViewModel
import com.example.retrofitcoroutines.viewmodel.MainViewModelFactory
import com.example.retrofitcoroutines.R
import com.example.retrofitcoroutines.view.adapter.MyAdapter
import com.example.retrofitcoroutines.service.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        button.setOnClickListener {

            val numberId = number_editText.text.toString()

            if( numberId != ""){
                viewModel.getOrganizedPost(Integer.parseInt(numberId), options)
            } else {
                viewModel.getPosts()
            }

            viewModel.myOrganizedResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body()?.let { it -> myAdapter.setData(it) }
                } else {
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })

            viewModel.myListResponse.observe(this, Observer { response ->
                if(response.isSuccessful){
                    response.body()?.let { it -> myAdapter.setData(it) }
                } else {
                    Toast.makeText(this, response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}