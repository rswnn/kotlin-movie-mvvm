package com.riswan.moviemvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riswan.moviemvvm.data.api.TheMovieInterFace
import com.riswan.moviemvvm.data.value_object.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkDataSource (private val apiService: TheMovieInterFace, private val compositeDisposable: CompositeDisposable){
    private val _networkstate = MutableLiveData<NetworkState>()

    val networkState:LiveData<NetworkState>
    get() = _networkstate

    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieResponse:LiveData<MovieDetails>
    get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {
        _networkstate.postValue(NetworkState.LOADING)

        try {

            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkstate.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkstate.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message.toString())
                        }
                    )
            )

        } catch (e: Exception){
            Log.e("MovieDetailsDataSource", e.message.toString())
        }
    }
}