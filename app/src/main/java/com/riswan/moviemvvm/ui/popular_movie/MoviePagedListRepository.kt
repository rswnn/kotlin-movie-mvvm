package com.riswan.moviemvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.riswan.moviemvvm.data.api.POST_PER_PAGE
import com.riswan.moviemvvm.data.api.TheMovieInterFace
import com.riswan.moviemvvm.data.repository.MovieDataSource
import com.riswan.moviemvvm.data.repository.MovieDataSourceFactory
import com.riswan.moviemvvm.data.repository.NetworkState
import com.riswan.moviemvvm.data.value_object.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService: TheMovieInterFace) {
    lateinit var moviePageList: LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchMoviePagedList(compositeDisposable: CompositeDisposable):LiveData<PagedList<Movie>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePageList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePageList
    }

    fun getNetWorkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movieDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState
        )
    }
}