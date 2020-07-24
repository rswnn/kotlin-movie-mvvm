package com.riswan.moviemvvm.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    ENDOFFILE
}

//51eccbf205ac5712b41444ee69c0d6ac

class NetworkState (val status:Status, val msg: String) {
    companion object {
        val LOADED : NetworkState;
        val LOADING : NetworkState;
        val ERROR : NetworkState;
        val ENDOFFILE: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
            ERROR = NetworkState(Status.FAILED, "Something went wrong")
            ENDOFFILE = NetworkState(Status.ENDOFFILE, "You have reached the end")
        }
    }
}