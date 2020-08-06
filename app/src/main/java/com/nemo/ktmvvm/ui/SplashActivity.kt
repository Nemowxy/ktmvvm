package com.nemo.ktmvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nemo.ktmvvm.config.SpKey
import com.nemo.ktmvvm.ui.launch.LauncherActivity
import com.nemo.ktmvvm.ui.main.MainActivity
import me.goldze.mvvmhabit.utils.SPUtils

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!SPUtils.getInstance().getBoolean(SpKey.IS_FIRST,true)){
            startActivity(Intent(this,MainActivity::class.java))
        } else {
            startActivity(Intent(this,LauncherActivity::class.java))
        }
        finish()
    }
}