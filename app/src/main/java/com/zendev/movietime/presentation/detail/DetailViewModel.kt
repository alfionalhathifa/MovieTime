package com.zendev.movietime.presentation.detail

import androidx.lifecycle.ViewModel
import com.zendev.movietime.core.domain.model.Movie
import com.zendev.movietime.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}