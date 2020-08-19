package com.nemo.ktmvvm.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.azhon.basic.lifecycle.BaseViewModel
import com.nemo.ktmvvm.net.entity.ArticleEntity
import com.nemo.ktmvvm.net.entity.BannerEntity
import com.nemo.ktmvvm.net.source.main.MainApiClientImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(application: Application, model: MainApiClientImpl?) : BaseViewModel() {

    var bannerEntity = MutableLiveData<List<BannerEntity>>()
    var listArticle = MutableLiveData<ArticleEntity>()

    init {
        val subscribe = model!!.getBanner()
                .map { s ->
                    Log.d(TAG, "apply: $s")
                    s.data
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d(TAG, "onNext: $it")
                            bannerEntity.value = it
                        },
                        {
                            Log.d(TAG, "onError: $it")
                        },
                        {
                            Log.d(TAG, "onComplete: ")
                        },
                        {
                            Log.d(TAG, "onSubscribe: ")
                        }
                )
//
        model.articleList()
                .map{
                    s -> s.data
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { listArticle.value = it }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}