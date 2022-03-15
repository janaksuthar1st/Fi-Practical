package com.fi.practicaljanak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.fi.practicaljanak.ui.home.HomeScreenActivity
import com.fi.practicaljanak.viewmodels.SplashViewModel

//Base Activity is not extended here because we do have layout here in splash UI
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.splashNavigate.observe(this) {
            if(it == true){
                HomeScreenActivity.startActivity(this)
                finish()
            }
        }
        viewModel.startTimer()
    }
}