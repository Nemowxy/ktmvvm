package com.nemo.ktmvvm.ui.fragment.category

import androidx.lifecycle.ViewModelProviders
import com.azhon.basic.base.BaseFragment
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.FragmentCategoryBinding

class CategoryFragment :BaseFragment<CategoryFragmentViewModel,FragmentCategoryBinding>(){
    override fun initViewModel(): CategoryFragmentViewModel {
        return ViewModelProviders.of(activity).get(CategoryFragmentViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun showError(obj: Any?) {

    }
}