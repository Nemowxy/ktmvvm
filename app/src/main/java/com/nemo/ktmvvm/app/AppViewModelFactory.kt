package com.nemo.ktmvvm.app

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl
import com.nemo.ktmvvm.ui.login.LoginViewModel
import com.nemo.ktmvvm.ui.main.MainViewModel

/**
 * 自定义ViewModel注入工厂
 */
class AppViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    private val mRepository: UserApiClientImpl? = null
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(mApplication, mRepository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApplication, mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: AppViewModelFactory? = null
        fun getInstance(application: Application): AppViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(AppViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AppViewModelFactory(application, Injection.provideDemoRepository())
                    }
                }
            }
            return INSTANCE
        }



        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private constructor(mApplication: Application, provideDemoRepository: UserApiClientImpl?) : this(mApplication)

}