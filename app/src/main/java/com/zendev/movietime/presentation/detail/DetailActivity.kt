package com.zendev.movietime.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.zendev.movietime.BuildConfig
import com.zendev.movietime.R
import com.zendev.movietime.core.domain.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            text_movie_name.text = detailMovie.title
            text_movie_overview.text = detailMovie.overview

            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + detailMovie.backdrop_path)
                .into(image_movie_back_poster)
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + detailMovie.poster_path)
                .into(image_movie)

            var statusFavorite = detailMovie.isFavorite

            setStatusFavorite(statusFavorite)
            btn_favorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            btn_favorite.text = getString(R.string.remove_favorite)
            btn_favorite.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorRemove)
        } else {
            btn_favorite.text = getString(R.string.add_favorite)
            btn_favorite.backgroundTintList = ContextCompat.getColorStateList(this, R.color.colorPrimary)
        }
    }
}