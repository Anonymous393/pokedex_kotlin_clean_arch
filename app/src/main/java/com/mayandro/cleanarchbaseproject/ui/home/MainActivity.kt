package com.mayandro.cleanarchbaseproject.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.ActivityMainBinding
import com.mayandro.cleanarchbaseproject.service.PokedexCrawlerService
import com.mayandro.cleanarchbaseproject.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(MainActivityViewModel::class), MainActivityViewInteractor {
    override fun layoutId(): Int = R.layout.activity_main

    override fun showAlertDialog(title: String, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        startService(PokedexCrawlerService.createStartingIntent(this))
    }
}