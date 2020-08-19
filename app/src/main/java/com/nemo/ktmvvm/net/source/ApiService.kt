package com.nemo.ktmvvm.net.source

import com.nemo.ktmvvm.net.HttpResult
import com.nemo.ktmvvm.net.entity.ArticleEntity
import com.nemo.ktmvvm.net.entity.BannerEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("banner/json")
    fun getBanner(): Observable<HttpResult<List<BannerEntity>>>

    @GET("article/list/{page}/json")
    fun articleList(@Path("page") page: Int): Observable<HttpResult<ArticleEntity>>
}