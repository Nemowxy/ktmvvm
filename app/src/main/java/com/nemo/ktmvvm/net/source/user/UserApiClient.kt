package com.nemo.ktmvvm.net.source.user

import com.nemo.ktmvvm.net.entity.UserEntity
import io.reactivex.Observable
import me.goldze.mvvmhabit.http.BaseResponse

/**
 * Created by goldze on 2019/3/26.
 */
interface UserApiClient {
    //模拟登录
    fun login(): Observable<Any?>?

    //模拟上拉加载
    fun loadMore(): Observable<UserEntity?>?
    fun demoGet(): Observable<BaseResponse<UserEntity?>?>?
    fun demoPost(catalog: String?): Observable<BaseResponse<UserEntity?>?>?
}