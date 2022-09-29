package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemLayautBinding
import com.example.retrofit.model.PostItem

class ItemAdapter:ListAdapter<PostItem,ItemAdapter.MyViewHolder>(diffUtil) {





class MyViewHolder(val binding: ItemLayautBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        val diffUtil=object :DiffUtil.ItemCallback<PostItem>(){
            override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
                return  oldItem.id==newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemLayautBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=getItem(position)
        holder.binding.apply {
            txtid.text=item.id.toString()
            userid.text=item.userId.toString()
            title.text=item.title
            body.text=item.body

        }

    }
}