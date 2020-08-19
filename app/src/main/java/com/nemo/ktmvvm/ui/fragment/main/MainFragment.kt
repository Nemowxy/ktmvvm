package com.nemo.ktmvvm.ui.fragment.main

import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.azhon.basic.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment :BaseFragment<MainFragmentViewModel,FragmentMainBinding>(){

    private var adapter: MainAdapter?=null


    override fun initViewModel(): MainFragmentViewModel {
        return ViewModelProviders.of(activity).get(MainFragmentViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        val banner: BGABanner = banner
        banner.setAutoPlayAble(true)
        banner.setAdapter(BGABanner.Adapter<ImageView,String> { banner, itemView, model, position ->
            Glide.with(activity)
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
        val manager = LinearLayoutManager(activity)
        adapter = MainAdapter()
        rvNews.layoutManager = manager
        rvNews.adapter = adapter
    }

    override fun initData() {

    }

    override fun showError(obj: Any?) {

    }
}