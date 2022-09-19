package com.app.obvious.utils

sealed class Response<out T> {
    class Success<T>(val data:T) : Response<T>()
    class Error(val msg: String) : Response<Nothing>()
}