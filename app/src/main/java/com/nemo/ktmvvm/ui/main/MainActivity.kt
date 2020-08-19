package com.nemo.ktmvvm.ui.main

import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.azhon.basic.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.app.AppViewModelFactory
import com.nemo.ktmvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    private var adapter:MainAdapter?=null

    override fun initContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
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

        viewModel.listArticle.observe(this, Observer { its ->
            adapter?.setNewData(its.datas)
        })

        val rvNews: RecyclerView = dataBinding.rvNews
        val manager = LinearLayoutManager(this)
        adapter = MainAdapter()
        rvNews.layoutManager = manager
        rvNews.adapter = adapter
    }

    override fun showError(obj: Any?) {

    }

    override fun initViewModel(): MainViewModel {
        val factory = AppViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
    }

    override fun initData() {

    }
}