package com.riswan.moviemvvm.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.riswan.moviemvvm.data.api.TheMovieInterFace
import com.riswan.moviemvvm.data.value_object.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(private val apiService: TheMovieInterFace, private val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {

        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}