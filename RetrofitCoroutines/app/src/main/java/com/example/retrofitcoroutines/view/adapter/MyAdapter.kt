package com.example.retrofitcoroutines.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcoroutines.R
import com.example.retrofitcoroutines.service.model.Post
import kotlinx.android.synthetic.main.post_cell.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_cell, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userId_txt.text = myList[position].userId.toString()
        holder.itemView.id_txt.text = myList[position].id.toString()
        holder.itemView.title_txt.text = myList[position].title
        holder.itemView.body_txt.text = myList[position].body
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}