package com.nemo.ktmvvm.net

data class HttpResult<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String


) {
    override fun toString(): String {
        return "HttpResult(data=$data, errorCode=$errorCode, errorMsg='$errorMsg')"
    }
}