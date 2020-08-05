package com.nemo.ktmvvm.net.source.user

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
interface UserApiService {
    @GET("action/apiv2/banner?catalog=1")
    fun demoGet(): Observable<BaseResponse<UserEntity?>?>?

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    fun demoPost(@Field("catalog") catalog: String?): Observable<BaseResponse<UserEntity?>?>?
}