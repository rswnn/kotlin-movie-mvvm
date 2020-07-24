package com.riswan.moviemvvm.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val api_key = "51eccbf205ac5712b41444ee69c0d6ac"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w780/"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object TheMovieDBClient {

    fun getClient(): TheMovieInterFace {
        val requestInterceptor = Interceptor {chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", api_key)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieInterFace::class.java)
    }

}

//https://api.themoviedb.org/3/movie/popular?api_key=51eccbf205ac5712b41444ee69c0d6ac&language=en-US&page=1
//https://api.themoviedb.org/3/movie/516486?api_key=51eccbf205ac5712b41444ee69c0d6ac&language=en-US
//https://api.themoviedb.org/3/