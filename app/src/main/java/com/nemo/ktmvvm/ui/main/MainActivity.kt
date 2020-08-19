package com.nemo.ktmvvm.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azhon.basic.base.BaseActivity
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.databinding.ActivityMainBinding
import com.nemo.ktmvvm.ui.fragment.category.CategoryFragment
import com.nemo.ktmvvm.ui.fragment.favorite.FavoriteFragment
import com.nemo.ktmvvm.ui.fragment.main.MainFragment
import com.nemo.ktmvvm.ui.fragment.person.PersonFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    private val homeTabIndex = 0
    private val favoritesTabIndex = 1
    private val schedulesTabIndex = 2
    private val mineTabIndex = 3

    override fun initContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val viewPager = view_pager
        val bottomView = bottom_view

        val fragments = getFragments()

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }

            override fun getItemCount(): Int {
                return fragments.size
            }
        }
        //禁用左右滑动切换页签
//        viewPager.isUserInputEnabled = false

        bottomView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.setCurrentItem(homeTabIndex, false)
                }
                R.id.navigation_dashboard -> {
                    viewPager.setCurrentItem(favoritesTabIndex, false)
                }
                R.id.navigation_notifications -> {
                    viewPager.setCurrentItem(schedulesTabIndex, false)
                }
                R.id.navigation_person -> {
                    viewPager.setCurrentItem(mineTabIndex, false)
                }
            }
            true
        }
    }

    override fun showError(obj: Any?) {

    }

    override fun initViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun initData() {

    }


    private fun getFragments(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>(4)

        val homeFragment = MainFragment()

        val favoritesFragment = FavoriteFragment()

        val schedulesFragment = CategoryFragment()

        val mineFragment = PersonFragment()
        fragments.add(homeFragment)
        fragments.add(favoritesFragment)
        fragments.add(schedulesFragment)
        fragments.add(mineFragment)
        return fragments
    }
}