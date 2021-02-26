package com.fullpagedeveloper.viewview

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.viewbinding.ViewBinding
import com.fullpagedeveloper.viewview.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    var anim_from_ottom: Animation? = null
    var binding: ViewBinding? = null

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val utils = Utils()
        utils.hideStatusBarNavBar(window)

        hideSystemUI()

        animContent(binding as @NonNull ActivityThirdBinding)

        animBackPress(binding as @NonNull ActivityThirdBinding)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animBackPress(@NonNull binding: ActivityThirdBinding) {
        binding.ivDownArrow.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(binding.ivDownArrow, "background_image_transition")
            )
            startActivity(intent, options.toBundle())
        }
    }

    private fun animContent(@NonNull binding: ActivityThirdBinding) {
        anim_from_ottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom)

        binding.svThirdActivity.animation = anim_from_ottom
        binding.ivDownArrow.animation = anim_from_ottom
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