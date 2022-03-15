package com.fi.practicaljanak.ui.home

import android.content.Context
import android.content.Intent
import com.fi.practicaljanak.R
import com.fi.practicaljanak.base.BaseActivity
import com.fi.practicaljanak.databinding.ActivityHomeScreenBinding
import com.fi.practicaljanak.extensions.getClassName
import com.fi.practicaljanak.ui.home.fragment.HomeFragment
import com.fi.practicaljanak.viewmodels.HomeScreenViewModel
import kotlin.reflect.KClass

class HomeScreenActivity : BaseActivity<HomeScreenViewModel, ActivityHomeScreenBinding>() {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): ActivityHomeScreenBinding =
        ActivityHomeScreenBinding.inflate(layoutInflater)


    override fun initView() {
        replaceFragment()
    }

    override fun listeners() { //No listener to be handle here
    }

    private fun replaceFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.flContainer,
            HomeFragment.newInstance(),
            HomeFragment.getClassName()
        ) //fragmentTransaction.addToBackStack(AirQualityListFragment.getClassName())
        fragmentTransaction.commit()
    }

//    override fun onBackPressed() {
//        fragmentManager.popBackStackImmediate()
//        super.onBackPressed()
//    }

    companion object {
        fun startActivity(context: Context) {
            val mIntent = Intent(context, HomeScreenActivity::class.java)
            context.startActivity(mIntent)
        }
    }
}