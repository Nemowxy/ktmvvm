package com.nemo.ktmvvm.app

import android.app.Application
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.ui.main.MainActivity
import com.nemo.ktmvvm.utils.Utils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //是否开启日志打印
        Utils.init(this)
    }
}