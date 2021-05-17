package com.rexbot.bitrtix.bot.ui.account

import android.graphics.Color
import kotlin.random.Random

class ResourcesUtils {




    companion object {
        private val colors = arrayOf(
            Color.parseColor("#1082FF"),
            Color.parseColor("#8F55EE"),
            Color.parseColor("#5564EE"),
            Color.parseColor("#55D2EE"),
            Color.parseColor("#EE6855")
        )

        fun getRandomColor(): Int = colors[Random.nextInt(colors.size)]

    }
}