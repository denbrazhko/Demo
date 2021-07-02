package com.rexbot.bitrtix.bot.ui.splash

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.view.children
import com.rexbot.bitrtix.bot.App

class Ut {

    companion object {
        @SuppressLint("ClickableViewAccessibility")
        val viewTouchListener =
            View.OnTouchListener { v, event ->
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    onPressed(v)
                    true
                } else {
                    onUp(v)
                    false
                }
            }

        private fun onPressed(view: View) {
            if (view is ViewGroup) {
                for (child in view.children) {
                    if (child is ViewGroup)
                        onPressed(child)
                    else
                        child.isPressed = true
                }
            }
        }

        private fun onUp(view: View) {
            if (view is ViewGroup) {
                for (child in view.children) {
                    if (child is ViewGroup)
                        onPressed(child)
                    else
                        child.isPressed = false
                }
            }
        }
    }
}