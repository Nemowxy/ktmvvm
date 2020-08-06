package com.nemo.ktmvvm.ui.main

import android.app.Application
import android.util.Log
import com.nemo.ktmvvm.net.entity.BannerEntity
import com.nemo.ktmvvm.net.source.main.MainApiClientImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent




class MainViewModel(application: Application, model: MainApiClientImpl?) : BaseViewModel<MainApiClientImpl>(application, model) {

    var bannerEntity = SingleLiveEvent<List<BannerEntity>>()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: " + (model == null))
        model!!.getBanner()
                .map { s ->
                    Log.d(TAG, "apply: " + s.toString())
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
                            Log.d(TAG, "onError: " + it.toString())
                        },
                        {
                            Log.d(TAG, "onComplete: ")
                        },
                        {
                            Log.d(TAG, "onSubscribe: ")
                        }
                )
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}