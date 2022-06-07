package com.project.natheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.natheroes.databinding.RowItemHeroesGirlsBinding
import com.project.natheroes.response.HeroesGirlsResponse
import com.project.natheroes.ui.OnItemClickCallback

class HeroesGirlsAdapter : RecyclerView.Adapter<HeroesGirlsAdapter.MyViewHolder>() {

    private var listData = ArrayList<HeroesGirlsResponse>()

    fun setDatas(newList: List<HeroesGirlsResponse>) {
        if (newList == null) return
        listData.clear()
        listData.addAll(newList)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    /*fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }*/

    class MyViewHolder(val binding: RowItemHeroesGirlsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroesGirlsAdapter.MyViewHolder(
            RowItemHeroesGirlsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listData[position]
        holder.binding.apply {
            itemNameGirls.text = data.name
            Glide.with(itemImgGirls.context)
                .load(data.imageUrl)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemImgGirls)

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listData.size
}

