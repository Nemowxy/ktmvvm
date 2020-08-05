package com.nemo.ktmvvm.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nemo.ktmvvm.BR
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.app.AppViewModelFactory
import com.nemo.ktmvvm.databinding.ActivityMainBinding
import me.goldze.mvvmhabit.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewModel(): MainViewModel {
        val factory = AppViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
    }
}