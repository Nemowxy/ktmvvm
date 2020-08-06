package com.nemo.ktmvvm.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nemo.ktmvvm.BR
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.app.AppViewModelFactory
import com.nemo.ktmvvm.config.SpKey
import com.nemo.ktmvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.utils.SPUtils

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

    override fun initViewObservable() {
        val banner:BGABanner = banner
        banner.setAutoPlayAble(true)
        banner.setAdapter(BGABanner.Adapter<ImageView,String> { banner, itemView, model, position ->
            Glide.with(this@MainActivity)
                    .load(model)
                    .apply(RequestOptions().placeholder(R.drawable.holder))
                    .apply(RequestOptions().error(R.drawable.holder))
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().dontAnimate())
                    .into(itemView)
        })

        viewModel.bannerEntity.observe(this, Observer {its->
            val list = ArrayList<String>()
            its.map { list.add(it.imagePath)}
            banner.setData(list, emptyList())
        })
    }
}