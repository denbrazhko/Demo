package com.rexbot.bitrtix.bot.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.util.concurrent.HandlerExecutor
import com.google.android.gms.safetynet.SafetyNet
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.BuildConfig
import com.rexbot.bitrtix.bot.ui.signup.SignUpActivity
import com.rexbot.bitrtix.bot.databinding.AcitivtyLoginBinding
import com.rexbot.bitrtix.bot.network.RequestStatus
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.ui.common.BaseActivity
import com.rexbot.bitrtix.bot.ui.main.MainActivity

class LoginActivity : BaseActivity<AcitivtyLoginBinding>() {

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        init()
        initObservers()
    }

    override fun init() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(LoginViewModel::class.java)

        binding.etUsername.setText(viewModel.getCreds() ?: "")
    }

    override fun initObservers() {
        binding.btnSignIn.setOnClickListener {
            viewModel.signIn(
                binding.etUsername.text.toString().trim(),
                binding.etPass.text
            )
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.cbRecaptcha.setOnCheckedChangeListener { b, isChecked ->
            recaptchaOnClick(
                b,
                isChecked
            )
        }
        binding.etPass.addTextChangedListener(textWatcher)
        binding.etUsername.addTextChangedListener(textWatcher)

        viewModel.signInLiveData.observe(this, loginObserver)
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
                        Log.w(TAG, "Error: $str")
                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private val loginObserver =
        Observer<Resource<SignInResponseModel>> {
            when (it?.status) {
                RequestStatus.LOADING -> binding.progress.root.visibility = VISIBLE
                RequestStatus.SUCCESS -> {
                    binding.progress.root.visibility = GONE
                    successSignIn()
                }
                RequestStatus.ERROR -> {
                    binding.progress.root.visibility = GONE
                    errorSignIn(it.message ?: "505")
                }
            }
        }

    private fun successSignIn() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun errorSignIn(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
            Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
           validateFields()
        }
    }

    private fun validateFields() {
        binding.btnSignIn.isEnabled = binding.etPass.isValid &&
                binding.etUsername.text.toString().isNotEmpty() &&
                binding.cbRecaptcha.isChecked
    }

    override fun getViewBinding(): AcitivtyLoginBinding =
        AcitivtyLoginBinding.inflate(layoutInflater)

//    override fun getViewModel(): AcitivtyLoginBinding = AcitivtyLoginBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "LOGIN_TAG"
    }
}