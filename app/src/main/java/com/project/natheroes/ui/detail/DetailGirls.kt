package com.project.natheroes.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.natheroes.databinding.ActivityDetailGirlsBinding
import com.project.natheroes.response.HeroesGirlsResponse

class DetailGirls : AppCompatActivity() {

    private var _binding: ActivityDetailGirlsBinding? = null
    private val binding get() = _binding as ActivityDetailGirlsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailGirlsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<HeroesGirlsResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {
                Glide.with(this@DetailGirls).load(data.imageUrl).into(detailImageGirls)
                detailNamaGirls.text = data.name
                detailUsiaGirls.text = "Di Tahun : " + data.birthYear + "Lahirnya"
                detailMatiGirls.text = "Wafat : " + data.deathYear
                detailDeskGirls.text = data.description
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}