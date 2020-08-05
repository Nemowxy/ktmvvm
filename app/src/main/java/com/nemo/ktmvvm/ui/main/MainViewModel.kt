package com.nemo.ktmvvm.ui.main

import android.app.Application
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl
import me.goldze.mvvmhabit.base.BaseViewModel

class MainViewModel(application: Application, model: UserApiClientImpl?) : BaseViewModel<UserApiClientImpl>(application, model) {
}