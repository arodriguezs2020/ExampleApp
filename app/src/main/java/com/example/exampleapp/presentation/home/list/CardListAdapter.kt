package com.example.exampleapp.presentation.home.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.exampleapp.R
import com.example.exampleapp.databinding.RowCardListBinding
import com.example.exampleapp.model.Cards

class CardListAdapter(var items: List<Cards>) :
    RecyclerView.Adapter<CardListAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Cards)
    }

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.name
        holder.tvSubtitle.text = item.type
        holder.tvDescription.text = item.text

        Glide.with(holder.imageView)
            .load(item.imageUrl)
            .placeholder(R.drawable.no_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)

        holder.cardView.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fillList(items: List<Cards>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: RowCardListBinding) : RecyclerView.ViewHolder(binding.root) {
        val cardView: CardView = binding.cardView
        val tvTitle: TextView = binding.tvTitle
        val tvSubtitle: TextView = binding.tvSubtitle
        val tvDescription: TextView = binding.tvDescription
        val imageView: ImageView = binding.imageView
    }

}