package com.nemo.ktmvvm.net

import android.text.TextUtils
import android.util.Log
import com.nemo.ktmvvm.config.GlobalConfig
import com.nemo.ktmvvm.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by goldze on 2017/5/10.
 * RetrofitClient封装单例类, 实现网络请求
 */
class RetrofitClient private constructor(
    url: String = GlobalConfig.BASE_URL,
    headers: Map<String, String>? = null
) {
    private var cache: Cache? = null
    private var httpCacheDirectory: File? = null

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the `service` interface.
     */
    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit.create(service)
    }

    companion object {

        private val mContext = Utils.getContext()
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var retrofit: Retrofit
        val instance: RetrofitClient
            get() = SingletonHolder.INSTANCE

        /**
         * / **
         * execute your customer API
         * For example:
         * MyApiService service =
         * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
         *
         *
         * RetrofitClient.getInstance(MainActivity.this)
         * .execute(service.lgon("name", "password"), subscriber)
         * * @param subscriber
         */
        fun <T> execute(
            observable: Observable<T>,
            subscriber: Observer<T>?
        ): T? {
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber!!)
            return null
        }
    }

    init {
        var url: String? = url
        if (TextUtils.isEmpty(url)) {
            url = GlobalConfig.BASE_URL
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory =
                File(mContext.cacheDir, "goldze_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, GlobalConfig.CACHE_TIMEOUT.toLong())
            }
        } catch (e: Exception) {
            Log.e("Nemo","Could not create http cache", e)
        }
        val sslParams = HttpsUtils.getSslSocketFactory()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslParams!!.sSLSocketFactory, sslParams.trustManager)
            .connectTimeout(
                    GlobalConfig.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.SECONDS
            ).addInterceptor(logging)
            .writeTimeout(
                    GlobalConfig.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
            .connectionPool(
                ConnectionPool(
                    8,
                    15,
                    TimeUnit.SECONDS
                )
            ) // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
            .build()
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()
    }
}