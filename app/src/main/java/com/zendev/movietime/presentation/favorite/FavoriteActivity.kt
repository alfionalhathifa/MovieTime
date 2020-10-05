package com.zendev.movietime.presentation.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zendev.movietime.R
import com.zendev.movietime.core.ui.MovieAdapter
import com.zendev.movietime.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_empty.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.favorite_name)

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        showFavoriteMovieList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showFavoriteMovieList() {
        favoriteViewModel.favoriteMovie.observe(this, Observer { dataMovie ->
            movieAdapter.setData(dataMovie)
            ll_404.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_movie_favorite) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}

