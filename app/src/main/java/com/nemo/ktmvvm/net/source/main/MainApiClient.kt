package com.nemo.ktmvvm.net.source.main

import com.nemo.ktmvvm.net.HttpResult
import com.nemo.ktmvvm.net.entity.ArticleEntity
import com.nemo.ktmvvm.net.entity.BannerEntity
import io.reactivex.Observable
/**
 * Created by goldze on 2019/3/26.
 */
interface MainApiClient {

    /**
     * 获取首页轮播图
     */
    fun getBanner(): Observable<HttpResult<List<BannerEntity>>>

    /**
     * 首页文章列表
     */
    fun articleList(): Observable<HttpResult<ArticleEntity>>
}