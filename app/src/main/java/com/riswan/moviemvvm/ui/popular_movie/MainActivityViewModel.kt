package com.riswan.moviemvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.riswan.moviemvvm.data.repository.NetworkState
import com.riswan.moviemvvm.data.value_object.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val movieRepository: MoviePagedListRepository): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchMoviePagedList(compositeDisposable)
    }

    val  networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetWorkState()
    }

    fun listIsEmpety():Boolean{
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}