package com.fullpagedeveloper.viewview

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fullpagedeveloper.viewview.databinding.ActivitySecondBinding
import android.util.Pair as UtilPair


class SecondActivity : AppCompatActivity() {

    var anim_from_bottom: Animation? = null
    var anim_from_left: Animation? = null
    var anim_from_right: Animation? = null


    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide statusbar dan navigationbar
        val utils = Utils()
        utils.hideStatusBarNavBar(window)

        hideSystemUI()

        animContent(binding)


        binding.ivArrowUp.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this, UtilPair.create(binding.groupMoreDetails, "background_image_transition"))
            startActivity(intent, options.toBundle())
        }

        binding.ivBackArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun animContent(@NonNull binding: ActivitySecondBinding) {
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left)
        anim_from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right)
        anim_from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom)

        binding.ivBackArrow.animation = anim_from_left
        binding.tvTitle.animation = anim_from_right
        binding.tvContent.animation = anim_from_right

        binding.ratingBarDetail.animation = anim_from_left
        binding.tvRatingNumber.animation = anim_from_right
        binding.tvRatingNumberSecond.animation = anim_from_right

        binding.ivArrowUp.animation = anim_from_bottom
        binding.tvMoteDetails.animation = anim_from_bottom
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