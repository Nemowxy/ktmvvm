package com.nemo.ktmvvm.net.source.main

import com.nemo.ktmvvm.net.HttpResult
import com.nemo.ktmvvm.net.entity.ArticleEntity
import com.nemo.ktmvvm.net.entity.BannerEntity
import io.reactivex.Observable
import me.goldze.mvvmhabit.base.BaseModel

/**
 * Created by goldze on 2019/3/26.
 */
class MainApiClientImpl private constructor(apiService: MainApiService) : BaseModel(), MainApiClient {
    private val apiService: MainApiService = apiService

    companion object {
        @Volatile
        private var INSTANCE: MainApiClientImpl? = null
        fun getInstance(apiService: MainApiService): MainApiClientImpl? {
            if (INSTANCE == null) {
                synchronized(MainApiClientImpl::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MainApiClientImpl(apiService)
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getBanner(): Observable<HttpResult<List<BannerEntity>>> {
        return apiService.getBanner()
    }

    override fun articleList(): Observable<HttpResult<List<ArticleEntity>>> {
        return apiService.articleList()
    }

}

