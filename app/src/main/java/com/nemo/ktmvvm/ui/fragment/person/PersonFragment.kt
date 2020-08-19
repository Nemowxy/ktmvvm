package com.nemo.ktmvvm.ui.fragment.person

import androidx.lifecycle.ViewModelProviders
import com.azhon.basic.base.BaseFragment
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.FragmentPersonBinding

class PersonFragment:BaseFragment<PersonFragmentViewModel,FragmentPersonBinding>() {
    override fun initViewModel(): PersonFragmentViewModel {
        return ViewModelProviders.of(activity).get(PersonFragmentViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.fragment_person
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun showError(obj: Any?) {

    }
}