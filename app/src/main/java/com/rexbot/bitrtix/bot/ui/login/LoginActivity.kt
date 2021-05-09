package com.rexbot.bitrtix.bot.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rexbot.bitrixbot.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.ui.signup.SignUpActivity
import com.rexbot.bitrtix.bot.databinding.AcitivtyLoginBinding
import com.rexbot.bitrtix.bot.network.RequestStatus
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.ui.common.BaseActivity

class LoginActivity : BaseActivity<AcitivtyLoginBinding>() {

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                binding.etUsername.text.toString(),
                binding.etPass.text.toString()
            )
        }
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.etPass.addTextChangedListener(textWatcher)
        binding.etUsername.addTextChangedListener(textWatcher)

        viewModel.signInLiveData.observe(this, loginObserver)
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
    }

    private fun errorSignIn(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            binding.btnSignIn.isEnabled = binding.etPass.text.isNotEmpty() &&
                    binding.etUsername.text.isNotEmpty()
        }
    }

    override fun getViewBinding(): AcitivtyLoginBinding =
        AcitivtyLoginBinding.inflate(layoutInflater)

//    override fun getViewModel(): AcitivtyLoginBinding = AcitivtyLoginBinding.inflate(layoutInflater)
}