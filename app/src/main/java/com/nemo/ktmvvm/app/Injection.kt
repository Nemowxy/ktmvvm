package com.nemo.ktmvvm.app

import com.nemo.ktmvvm.net.RetrofitClient.Companion.instance
import com.nemo.ktmvvm.net.source.main.MainApiClientImpl
import com.nemo.ktmvvm.net.source.main.MainApiClientImpl.Companion.getInstance
import com.nemo.ktmvvm.net.source.main.MainApiService
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl.Companion.getInstance
import com.nemo.ktmvvm.net.source.user.UserApiService

/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by goldze on 2019/3/26.
 */
object Injection {
    fun provideUserRepository(): UserApiClientImpl? {
        //网络API服务
        val apiService = instance.create(UserApiService::class.java)
        //网络数据源
        return getInstance(apiService)
    }

    fun provideMainRepository(): MainApiClientImpl? {
        //网络API服务
        val apiService = instance.create(MainApiService::class.java)
        //网络数据源
        return getInstance(apiService)
    }
}