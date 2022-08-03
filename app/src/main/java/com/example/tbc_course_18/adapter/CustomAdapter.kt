package com.example.tbc_course_18.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_course_18.databinding.GridViewBinding
import com.example.tbc_course_18.models.ApartmentsModel

typealias onClick = (item: ApartmentsModel.Content) -> Unit

class CustomAdapter():ListAdapter<ApartmentsModel,CustomAdapter.ViewHolder>(DiffCallBack()) {


    private var content = emptyList<ApartmentsModel.Content>()
    lateinit var onClick: onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder = ViewHolder(
        GridViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ViewHolder(private val binding:GridViewBinding):RecyclerView.ViewHolder(binding.root){


        private lateinit var currentItem: ApartmentsModel.Content
        fun bind(){
            currentItem = content[adapterPosition]
            binding.apply {
                titleTextView.text = currentItem.titleKA ?: "Title"
                Glide.with(this.root).load(currentItem.cover).into(appCompatImageView)
                root.setOnClickListener {
                    onClick(
                        currentItem
                    )
                }
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<ApartmentsModel>() {
        override fun areItemsTheSame(oldItem: ApartmentsModel, newItem: ApartmentsModel): Boolean {
            return oldItem.content?.get(0)?.titleKA == newItem.content?.get(0)?.titleKA
        }

        override fun areContentsTheSame(
            oldItem: ApartmentsModel,
            newItem: ApartmentsModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    fun setData(newList: List<ApartmentsModel.Content>){
        content = newList
        notifyDataSetChanged()
    }


}