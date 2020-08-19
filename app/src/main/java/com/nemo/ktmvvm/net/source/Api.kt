package com.nemo.ktmvvm.net.source

import com.azhon.basic.retrofit.BaseApi
import com.nemo.ktmvvm.config.GlobalConfig
import okhttp3.OkHttpClient

/**
 * 项目名:    TODO-MVVM
 * 包名       com.azhon.mvvm.api
 * 文件名:    Api
 * 创建时间:  2019-03-27 on 14:56
 * 描述:     TODO 使用Retrofit基础服务
 *
 * @author 阿钟
 */
class Api : BaseApi() {
    /**
     * 静态内部类单例
     */
    private object ApiHolder {
        private val api = Api()
        val apiService: ApiService = api.initRetrofit(GlobalConfig.BASE_URL)
                .create(ApiService::class.java)

    }

    /**
     * 做自己需要的操作
     */
    override fun setClient(): OkHttpClient? {
        return null
    }

    companion object {
        val instance: ApiService
            get() = ApiHolder.apiService
    }
}