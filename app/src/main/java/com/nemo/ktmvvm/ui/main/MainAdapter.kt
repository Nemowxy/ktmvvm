package com.nemo.ktmvvm.ui.main

import com.azhon.basic.adapter.BaseDBRVAdapter
import com.nemo.ktmvvm.BR
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.ItemMainBinding
import com.nemo.ktmvvm.net.entity.ArticleEntity

class MainAdapter(layout:Int= R.layout.item_main, bean:Int= BR.bean) : BaseDBRVAdapter<ArticleEntity.Data,ItemMainBinding>(layout,bean){


}