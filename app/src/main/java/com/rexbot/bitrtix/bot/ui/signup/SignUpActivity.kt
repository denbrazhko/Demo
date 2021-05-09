package com.rexbot.bitrtix.bot.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rexbot.bitrixbot.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.databinding.AcitivtySignupBinding
import com.rexbot.bitrtix.bot.network.RequestStatus
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.ui.common.BaseActivity
import com.rexbot.bitrtix.bot.ui.login.LoginViewModel

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
                binding.etPass.text.toString()
            )
        }

        viewModel.signUpLiveData.observe(this, signUpObserver)
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
    }

    private fun errorSignUp(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            binding.btnSignUp.isEnabled = binding.etPass.text.isNotEmpty() &&
                    binding.etUsername.text.isNotEmpty() &&
                    binding.etPassAgain.text.isNotEmpty() &&
                    binding.etPass.text.toString() == binding.etPassAgain.text.toString()
        }
    }

    override fun getViewBinding(): AcitivtySignupBinding =
        AcitivtySignupBinding.inflate(layoutInflater)
}