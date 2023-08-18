package com.jumbojay.traveltaipeidemo.ui.attractionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jumbojay.traveltaipeidemo.R
import com.jumbojay.traveltaipeidemo.data.Attractions
import com.jumbojay.traveltaipeidemo.databinding.ItemViewBinding

class AttractionsAdapter : PagingDataAdapter<Attractions, AttractionsAdapter.AttractionsViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Attractions>() {
            override fun areItemsTheSame(oldItem: Attractions, newItem: Attractions): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Attractions, newItem: Attractions): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class AttractionsViewHolder(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    var onClick: ((Attractions) -> Unit)? = null

    override fun onBindViewHolder(holder: AttractionsViewHolder, position: Int) {
        val binding = holder.binding
        getItem(position)?.let { attractions ->
            binding.itemTitle.text = attractions.name
            binding.itemContent.text = attractions.introduction

            Glide.with(binding.itemImg)
                .load(
                    if (attractions.images.isNotEmpty()) {
                        attractions.images.first().src
                    } else {
                        ContextCompat.getDrawable(
                            binding.itemImg.context,
                            R.drawable.baseline_broken_image_24
                        )
                    }
                )
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.itemImg)
            binding.root.setOnClickListener {
                onClick?.invoke(attractions)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionsViewHolder(binding)
    }
}