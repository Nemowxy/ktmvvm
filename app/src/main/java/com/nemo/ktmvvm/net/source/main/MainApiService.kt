package com.nemo.ktmvvm.net.source.main

import com.nemo.ktmvvm.net.HttpResult
import com.nemo.ktmvvm.net.entity.ArticleEntity
import com.nemo.ktmvvm.net.entity.BannerEntity
import com.nemo.ktmvvm.net.entity.UserEntity
import io.reactivex.Observable
import me.goldze.mvvmhabit.http.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by goldze on 2017/6/15.
 */
interface MainApiService {
    @GET("banner/json")
    fun getBanner(): Observable<HttpResult<List<BannerEntity>>>

    @GET("article/list/{page}/json")
    fun articleList(): Observable<HttpResult<List<ArticleEntity>>>
}