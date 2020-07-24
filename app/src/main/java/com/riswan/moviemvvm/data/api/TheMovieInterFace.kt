package com.riswan.moviemvvm.data.api

import com.riswan.moviemvvm.data.value_object.MovieDetails
import com.riswan.moviemvvm.data.value_object.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieInterFace {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id:Int):Single<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page:Int):Single<MovieResponse>

}

