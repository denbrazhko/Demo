package com.rexbot.bitrtix.bot

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        setContentView(tv)
        tv.setText("reklj")
    }
}