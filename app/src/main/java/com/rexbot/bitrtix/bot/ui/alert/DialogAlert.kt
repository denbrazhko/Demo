package com.rexbot.bitrtix.bot.ui.alert

import android.content.Context
import android.view.View
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.databinding.DialogAlertBinding
import com.rexbot.bitrtix.bot.ui.common.BaseDialog

class DialogAlert(
    context: Context,
    private val title: String,
    private val description: String,
    private val isError: Boolean = false,
    private val secondaryBtnText: String? = null,
    private val onClickListener: DialogAlertOnClickListener
) : BaseDialog<DialogAlertBinding>(context) {


    override fun init() {
        if (isError)
            binding.iv.setImageDrawable(getDrawable(iconError))
        else binding.iv.setImageDrawable(getDrawable(iconOk))
        if (secondaryBtnText != null) {
            binding.btnSecondary.visibility = View.VISIBLE
            binding.btnSecondary.text = secondaryBtnText
            binding.btnSecondary.setOnClickListener {
                onClickListener.onSecondaryClick()
                dismiss()
            }
        }
        binding.tvTitle.text = title
        binding.tvDescription.text = description
    }

    override fun initListeners() {
        binding.btnPrimary.setOnClickListener {
            onClickListener.onPrimaryClick()
            dismiss()
        }
    }


    override fun getViewBinding(): DialogAlertBinding = DialogAlertBinding.inflate(layoutInflater)

    interface DialogAlertOnClickListener {
        fun onPrimaryClick()
        fun onSecondaryClick()
    }

    companion object {
        private const val iconOk = R.drawable.ic_alert_ok
        private const val iconError = R.drawable.ic_alert_error
    }
}