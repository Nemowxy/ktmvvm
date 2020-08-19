package com.nemo.ktmvvm.net.source.user

import com.nemo.ktmvvm.net.HttpResult
import com.nemo.ktmvvm.net.entity.UserEntity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by goldze on 2019/3/26.
 */
class UserApiClientImpl private constructor(apiService: UserApiService) :UserApiClient {
    private val apiService: UserApiService = apiService
    override fun login(): Observable<Any?>? {
        return Observable.just(Any())
            .delay(3, TimeUnit.SECONDS) //延迟3秒
    }

    override fun loadMore(): Observable<UserEntity> {
        return Observable.create(ObservableOnSubscribe<UserEntity> { observableEmitter ->
            val entity = UserEntity()
            val itemsEntities: MutableList<UserEntity.ItemsEntity> =
                ArrayList<UserEntity.ItemsEntity>()
            //模拟一部分假数据
            for (i in 0..9) {
                val item = UserEntity.ItemsEntity()
                item.id = -1
                item.name = "模拟条目"
                itemsEntities.add(item)
            }
            entity.items = itemsEntities
            observableEmitter.onNext(entity)
        }).delay(3, TimeUnit.SECONDS) //延迟3秒
    }

    override fun demoGet(): Observable<HttpResult<UserEntity>> {
        TODO("Not yet implemented")
    }

    override fun demoPost(catalog: String?): Observable<HttpResult<UserEntity>> {
        TODO("Not yet implemented")
    }

    companion object {
        @Volatile
        private var INSTANCE: UserApiClientImpl? = null
        fun getInstance(apiService: UserApiService): UserApiClientImpl? {
            if (INSTANCE == null) {
                synchronized(UserApiClientImpl::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = UserApiClientImpl(apiService)
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}

