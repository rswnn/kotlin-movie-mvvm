package com.riswan.moviemvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.riswan.moviemvvm.data.api.TheMovieInterFace
import com.riswan.moviemvvm.data.repository.MovieDetailsNetworkDataSource
import com.riswan.moviemvvm.data.repository.NetworkState
import com.riswan.moviemvvm.data.value_object.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService :TheMovieInterFace) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId:Int): LiveData<MovieDetails>{
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailNetoworkState(): LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }
}