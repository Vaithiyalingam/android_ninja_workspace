package com.vaithidroid.appone.designstringtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vaithidroid.appone.designstringtask.databinding.UsersRowLayoutBinding
import com.vaithidroid.appone.designstringtask.models.Data

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: UsersRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data){
            binding.data = data
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent : ViewGroup) : MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UsersRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.avatar == newItem.avatar
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = differ.currentList[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}