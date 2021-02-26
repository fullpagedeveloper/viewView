package com.fullpagedeveloper.viewview

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.fullpagedeveloper.viewview.databinding.ActivityMainBinding
import com.fullpagedeveloper.viewview.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    var anim_from_bottom: Animation? = null
    var anim_from_top: Animation? = null
    var anim_from_left: Animation? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = inflate(layoutInflater)
        setContentView(binding.root)

        animationCard(binding)

        //hide statusbar dan navigationbar
        val utils = Utils()
        utils.hideStatusBarNavBar(window)

        hideSystemUI()

        modeTheme(binding)

        binding.cardViewOne.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun animationCard(@NonNull binding: ActivityMainBinding) {
        anim_from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom)
        anim_from_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top)
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left)

        binding.cardViewOne.animation = anim_from_bottom
        binding.cardViewTwo.animation = anim_from_bottom
        binding.cardViewThree.animation = anim_from_bottom

        binding.ivSettings.animation = anim_from_top
        binding.textView.animation = anim_from_top

        binding.searchView.animation = anim_from_left
    }

    private fun modeTheme(@NonNull binding: ActivityMainBinding) {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.ivSettings.setColorFilter(ContextCompat.getColor(applicationContext, R.color.white))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            this.window.decorView.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}