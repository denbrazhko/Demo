package com.rexbot.bitrtix.bot.ui.common

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.rexbot.bitrtix.bot.R

abstract class BaseDialog<VB : ViewBinding>(
    context: Context,
    private val widthPercentage: Int = 90,
    private val heightPercentage: Int = 0
) : Dialog(context, R.style.AlertDialog_AppCompat_Floating) {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        setSize()
        init()
        initListeners()
    }

    abstract fun getViewBinding(): VB
    abstract fun init()
    abstract fun initListeners()

    fun getString(id: Int): String = context.getString(id)
    fun getDrawable(id: Int): Drawable = ContextCompat.getDrawable(context, id)!!


    private fun setSize() {
        if (window == null)
            return
        var width: Int
        var height: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = window!!.windowManager.currentWindowMetrics
            val insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars()
                )
            width = windowMetrics.bounds.width() - insets.left - insets.right
            height = windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            window!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            height = displayMetrics.heightPixels
        }

        width = width * widthPercentage / 100
        height = if (heightPercentage > 0)
            height * heightPercentage / 100
        else WindowManager.LayoutParams.WRAP_CONTENT

        val params = window!!.attributes
        params.width = width
        params.height = height

        window!!.attributes = params
        window!!.setGravity(Gravity.CENTER)
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    companion object {
        private const val TAG = "BASE_DIALOG_TAG"
    }
}