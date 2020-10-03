package com.zendev.movietime.core.utils

import com.zendev.movietime.core.data.source.local.entity.MovieEntity
import com.zendev.movietime.core.data.source.remote.response.MovieResponse
import com.zendev.movietime.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        isFavorite = input.isFavorite
    )
}