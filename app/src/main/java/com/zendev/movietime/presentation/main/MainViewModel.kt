package com.zendev.movietime.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zendev.movietime.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}