package com.project.natheroes.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.project.natheroes.R
import com.project.natheroes.adapter.HeroesAdapter
import com.project.natheroes.databinding.ActivityMainBinding
import com.project.natheroes.response.HeroesGirlsResponse
import com.project.natheroes.response.HeroesResponse
import com.project.natheroes.ui.detail.DetailActivity
import com.project.natheroes.ui.detail.DetailGirls


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == controller.graph.startDestinationId) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }
        NavigationUI.setupWithNavController(binding.navView, navController)

        viewModel.getHeroess()
        viewModel.getHeroesGirls()

        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.isError.observe(this) { showError(it) }
        viewModel.sejarahHeroes.observe(this) {
            showData(it)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun showData(data: List<HeroesResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = HeroesAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: HeroesResponse) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, data)
                    )
                }

                override fun onItemClicked(data: HeroesGirlsResponse) {
                    startActivity(
                        Intent(this@MainActivity, DetailGirls::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, data)
                    )
                }
            })
        }
    }

    private fun showError(isError: Throwable?) {
        Log.e("MainActivity", "showError: $isError")
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.progressMain.visibility = android.view.View.VISIBLE
        } else {
            binding.progressMain.visibility = android.view.View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }
}