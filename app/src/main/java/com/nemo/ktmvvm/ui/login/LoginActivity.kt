package com.nemo.ktmvvm.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nemo.ktmvvm.BR
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.app.AppViewModelFactory
import com.nemo.ktmvvm.databinding.ActivityLoginBinding

import me.goldze.mvvmhabit.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(){


    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int {
       return BR.viewModel
    }

    override fun initViewModel(): LoginViewModel {
        val factory:AppViewModelFactory = AppViewModelFactory.getInstance(application)!!
        return ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
    }
}