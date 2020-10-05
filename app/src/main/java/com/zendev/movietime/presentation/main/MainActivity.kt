package com.zendev.movietime.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zendev.movietime.R
import com.zendev.movietime.core.data.Resource
import com.zendev.movietime.core.ui.MovieAdapter
import com.zendev.movietime.presentation.detail.DetailActivity
import com.zendev.movietime.presentation.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_no_internet.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            showMovieList()
        }

        showMovieList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMovieList() {
        mainViewModel.movie.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressbar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        progressbar.visibility = View.GONE
                        ll_no_internet.visibility = View.VISIBLE
                        Toast.makeText(
                            this,
                            movie.message ?: getString(R.string.something_wrong),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })

        with(rv_movie) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}