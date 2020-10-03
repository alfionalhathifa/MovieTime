package com.zendev.movietime.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieId: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val isFavorite: Boolean
) : Parcelable