package com.rexbot.bitrtix.bot.ui.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.databinding.LayoutPasswordEdittextBinding
import com.rexbot.bitrtix.bot.utils.CryptAES

class PasswordEdittext @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var _binding: LayoutPasswordEdittextBinding? = null
    private val binding get() = _binding!!

    private var isHidden: Boolean = true


    init {
        _binding = LayoutPasswordEdittextBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PasswordEdittext,
            0, 0
        ).apply {
            try {
                binding.etPass.hint = getString(R.styleable.PasswordEdittext_passwordHint)
            } finally {
                recycle()
            }
        }
        binding.eye.setOnClickListener { changeHidden() }
    }

    val text get() = binding.etPass.text.toString()
    val encryptedText get() = CryptAES().encrypt(binding.etPass.text.toString())

    fun addTextChangedListener(watcher: TextWatcher) {
        binding.etPass.addTextChangedListener(watcher)
    }


    private fun changeHidden() {
        if (isHidden) {
            binding.etPass.transformationMethod = null
            binding.eye.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_eye_unlocked
                )
            )
        } else {
            binding.etPass.transformationMethod = PasswordTransformationMethod()
            binding.eye.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_eye_locked
                )
            )
        }

        isHidden = !isHidden
    }

    private val textWatcher =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                binding.etPass.isActivated = !isValid
            }
        }

    init {
        binding.etPass.addTextChangedListener(textWatcher)
    }

    val isValid get() = text.isNotEmpty() && text.length >= 8


    companion object {
        private const val TAG = "PASSWORD_ET_TAG"
    }
}