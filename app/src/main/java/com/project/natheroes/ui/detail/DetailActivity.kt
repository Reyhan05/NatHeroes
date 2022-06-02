package com.project.natheroes.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.HeroesResponse
import com.project.natheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<HeroesResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {
                Glide.with(this@DetailActivity).load(data.imageUrl).into(detailImage)
                detailNama.text = data.name
                detailUsia.text = "Di Tahun : " + data.birthYear
                detailMati.text = "Wafat : " + data.deathYear
                detailDesk.text = data.description
                detailAscensions.text = "Tahun Kenaikan : " + data.ascensionYear
            }
        }

    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}