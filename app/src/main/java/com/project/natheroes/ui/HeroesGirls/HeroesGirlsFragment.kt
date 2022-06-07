package com.project.natheroes.ui.HeroesGirls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.project.natheroes.adapter.HeroesGirlsAdapter
import com.project.natheroes.databinding.FragmentHeroesGirlsBinding
import com.project.natheroes.response.HeroesGirlsResponse
import com.project.natheroes.response.HeroesResponse
import com.project.natheroes.ui.MainViewModel
import com.project.natheroes.ui.OnItemClickCallback
import com.project.natheroes.ui.detail.DetailActivity
import com.project.natheroes.ui.detail.DetailGirls

class HeroesGirlsFragment : Fragment() {

    private var _binding: FragmentHeroesGirlsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesGirlsBinding.inflate(layoutInflater, container, false)

        viewModel.getHeroesGirls()

        viewModel.isError.observe(viewLifecycleOwner) { showError(it) }
        viewModel.heroesGirls.observe(viewLifecycleOwner) {
            showData(it)
        }

        return binding.root
    }

    private fun showData(data: List<HeroesGirlsResponse>) {
        binding.rvHeroesGirls.apply {
            val mAdapter = HeroesGirlsAdapter()
            mAdapter.setDatas(data)
            layoutManager = GridLayoutManager(context, 1)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: HeroesResponse) {
                    startActivity(
                        Intent(context, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, data)
                    )
                }

                override fun onItemClicked(data: HeroesGirlsResponse) {
                    startActivity(
                        Intent(context, DetailGirls::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, data)
                    )
                }
            })
        }
    }

    private fun showError(isError: Throwable?) {
        Log.e("MainActivity", "showError: $isError")
    }

}