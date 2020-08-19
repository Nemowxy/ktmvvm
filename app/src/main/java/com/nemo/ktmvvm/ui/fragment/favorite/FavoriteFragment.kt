package com.nemo.ktmvvm.ui.fragment.favorite

import androidx.lifecycle.ViewModelProviders
import com.azhon.basic.base.BaseFragment
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.FragmentFavoriteBinding

class FavoriteFragment:BaseFragment<FavoriteFragmentViewModel,FragmentFavoriteBinding>() {
    override fun initViewModel(): FavoriteFragmentViewModel {
        return ViewModelProviders.of(activity).get(FavoriteFragmentViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.fragment_favorite
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun showError(obj: Any?) {

    }
}