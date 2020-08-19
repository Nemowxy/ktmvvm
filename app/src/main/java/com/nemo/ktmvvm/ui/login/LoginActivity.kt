package com.nemo.ktmvvm.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.azhon.basic.base.BaseActivity
import com.nemo.ktmvvm.BR
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.app.AppViewModelFactory
import com.nemo.ktmvvm.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>(){


    override fun initViewModel(): LoginViewModel {
        val factory:AppViewModelFactory = AppViewModelFactory.getInstance(application)!!
        return ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun showError(obj: Any?) {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}