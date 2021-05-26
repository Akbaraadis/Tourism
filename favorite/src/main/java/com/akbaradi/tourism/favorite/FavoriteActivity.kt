package com.akbaradi.tourism.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbaradi.tourism.core.domain.model.Tourism
import com.akbaradi.tourism.core.ui.OnItemClickTourismCallback
import com.akbaradi.tourism.core.ui.TourismAdapter
import com.akbaradi.tourism.detail.DetailTourismActivity
import com.akbaradi.tourism.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: TourismAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(FavoriteModule().favoriteModule)

        supportActionBar?.title = "Favorite"

        favoriteAdapter = TourismAdapter()
        favoriteAdapter.notifyDataSetChanged()
        favoriteAdapter.setOnItemClick(object : OnItemClickTourismCallback {
            override fun onItemClickedTourism(tourism: Tourism) {
                Intent(this@FavoriteActivity, DetailTourismActivity::class.java).also {
                    it.putExtra(DetailTourismActivity.EXTRA_DATA, tourism)
                    startActivity(it)
                }
            }
        })

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }

        favoriteViewModel.favoriteTourism.observe(this,{
            favoriteAdapter.setData(it)

        })
    }
}