package com.jumbojay.traveltaipeidemo.ui.attractionsdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jumbojay.traveltaipeidemo.R
import com.jumbojay.traveltaipeidemo.data.Attractions
import com.jumbojay.traveltaipeidemo.databinding.ImageItemBinding

class ImagePagerAdapter : RecyclerView.Adapter<ImagePagerAdapter.ImagePagerViewHolder>() {

    private var images: List<Attractions.Image> = emptyList()

    fun setData(list: List<Attractions.Image>) {
        images = list
        notifyDataSetChanged()
    }

    inner class ImagePagerViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setUrl(url: String) {
            Glide.with(binding.image)
                .load(url)
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.image)
        }

        fun withoutImage() {
            Glide.with(binding.image)
                .load(R.drawable.baseline_broken_image_24)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagePagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (images.isEmpty()) 1 else images.size
    }

    override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
        if (images.isEmpty()) {
            holder.withoutImage()
        } else {
            val image = images[position]
            holder.setUrl(image.src)
        }

    }
}