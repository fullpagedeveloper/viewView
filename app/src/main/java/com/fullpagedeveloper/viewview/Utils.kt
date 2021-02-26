package com.fullpagedeveloper.viewview

import android.view.Window
import android.view.WindowManager

class Utils {

    fun hideStatusBarNavBar(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}