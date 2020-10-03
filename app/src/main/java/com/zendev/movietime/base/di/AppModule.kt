package com.zendev.movietime.base.di

import com.zendev.movietime.core.domain.usecase.MovieInteractor
import com.zendev.movietime.core.domain.usecase.MovieUseCase
import com.zendev.movietime.presentation.detail.DetailViewModel
import com.zendev.movietime.presentation.favorite.FavoriteViewModel
import com.zendev.movietime.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}