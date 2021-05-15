package com.rexbot.bitrtix.bot.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.util.concurrent.HandlerExecutor
import com.google.android.gms.safetynet.SafetyNet
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.BuildConfig
import com.rexbot.bitrtix.bot.databinding.AcitivtySignupBinding
import com.rexbot.bitrtix.bot.network.RequestStatus
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.ui.common.BaseActivity
import com.rexbot.bitrtix.bot.ui.login.LoginActivity

class SignUpActivity : BaseActivity<AcitivtySignupBinding>() {

    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initObservers()
    }


    override fun init() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(SignupViewModel::class.java)
    }

    override fun initObservers() {
        binding.etPass.addTextChangedListener(textWatcher)
        binding.etPassAgain.addTextChangedListener(textWatcher)
        binding.etUsername.addTextChangedListener(textWatcher)

        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(
                binding.etUsername.text.toString(),
                binding.etPass.text
            )
        }
        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.cbRecaptcha.setOnCheckedChangeListener { b, isChecked ->
            recaptchaOnClick(
                b,
                isChecked
            )
        }


        viewModel.signUpLiveData.observe(this, signUpObserver)
    }


    private fun recaptchaOnClick(v: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            val exec = HandlerExecutor(mainLooper)
            SafetyNet.getClient(this).verifyWithRecaptcha(BuildConfig.RECAPTCHA_KEY)
                .addOnSuccessListener(exec) {
                    if (it?.tokenResult?.isNotEmpty() == true) {
                        v.isChecked = true
                        v.isEnabled = false
                        validateFields()
                    }
                }
                .addOnFailureListener {
                    v.isChecked = false
                    v.isEnabled = true
                    if (it is ApiException) {
                        val statusCode = it.statusCode
                        val str = CommonStatusCodes
                            .getStatusCodeString(statusCode)
                        Log.w(LoginActivity.TAG, "Error: $str")
                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private val signUpObserver =
        Observer<Resource<SignupResponseModel>> {
            when (it?.status) {
                RequestStatus.LOADING -> binding.progress.root.visibility = View.VISIBLE
                RequestStatus.SUCCESS -> {
                    binding.progress.root.visibility = View.GONE
                    successSignUp()
                }
                RequestStatus.ERROR -> {
                    binding.progress.root.visibility = View.GONE
                    errorSignUp(it.message ?: "505")
                }
            }
        }

    private fun successSignUp() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun errorSignUp(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            validateFields()
        }
    }

    private fun validateFields() {
        binding.btnSignUp.isEnabled = binding.etUsername.text.toString().isNotEmpty() &&
                binding.etPass.isValid &&
                binding.etPassAgain.isValid &&
                binding.etPass.text == binding.etPassAgain.text &&
                binding.cbRecaptcha.isChecked
    }


    override fun getViewBinding(): AcitivtySignupBinding =
        AcitivtySignupBinding.inflate(layoutInflater)
}