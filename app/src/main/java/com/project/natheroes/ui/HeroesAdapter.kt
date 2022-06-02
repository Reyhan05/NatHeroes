package com.project.natheroes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.HeroesResponse
import com.project.natheroes.databinding.RowItemHeroesBinding

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.MyViewHolder>() {

    private var listHeroes = ArrayList<HeroesResponse>()

    fun setData(data: List<HeroesResponse>?) {
        if (data == null) return
        listHeroes.clear()
        listHeroes.addAll(data)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(val binding: RowItemHeroesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemHeroesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listHeroes[position]
        holder.binding.apply {
            itemName.text = data.name
            Glide.with(itemImg.context)
                .load(itemImg)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemImg)

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listHeroes.size
}