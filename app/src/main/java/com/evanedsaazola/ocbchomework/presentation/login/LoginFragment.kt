package com.evanedsaazola.ocbchomework.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.evanedsaazola.ocbchomework.NetworkClientInstance
import com.evanedsaazola.ocbchomework.R
import com.evanedsaazola.ocbchomework.SessionManager
import com.evanedsaazola.ocbchomework.data.model.LoginBodyPost
import com.evanedsaazola.ocbchomework.data.model.LoginItem
import com.evanedsaazola.ocbchomework.databinding.FragmentLoginBinding
import com.evanedsaazola.ocbchomework.hideSoftKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val menuNavController: NavController? by lazy { activity?.findNavController(R.id.fragmentContainerView) }
    private lateinit var sessionManager: SessionManager
    private lateinit var networkClientInstance: NetworkClientInstance

    private var username = ""
    private var password = ""

    private var textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            username = binding.etLoginUsername.editText?.text.toString()
            password = binding.etLoginPassword.editText?.text.toString()

            if (username.isNotEmpty()) {
                binding.etLoginUsername.error = null
            }
            if (password.isNotEmpty()) {
                binding.etLoginPassword.error = null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.etLoginUsername.editText?.addTextChangedListener(textWatcher)
        binding.etLoginPassword.editText?.addTextChangedListener(textWatcher)

        binding.btnLogin.setOnClickListener {
            it.hideSoftKeyboard()
            validateLogin()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToDashboard(accountHolder: String?) {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToDashboardFragment(accountHolder)
        menuNavController?.navigate(directions)
    }

    private fun validateLogin() {
        username = binding.etLoginUsername.editText?.text.toString()
        password = binding.etLoginPassword.editText?.text.toString()

        if (username.isBlank() && password.isBlank()) {
            binding.etLoginUsername.error = getString(R.string.label_error_required, "username")
            binding.etLoginPassword.error = getString(R.string.label_error_required, "password")
        } else if (username.isBlank()) {
            binding.etLoginUsername.error = getString(R.string.label_error_required, "username")
        } else if (password.isBlank()) {
            binding.etLoginPassword.error = getString(R.string.label_error_required, "password")
        } else {
            submitLogin()
        }
    }

    private fun submitLogin() {
        username = binding.etLoginUsername.editText?.text.toString()
        password = binding.etLoginPassword.editText?.text.toString()

        val loginBody = LoginBodyPost(
            username = username,
            password = password
        )

        networkClientInstance = NetworkClientInstance(requireContext())
        sessionManager = SessionManager(requireContext())

        networkClientInstance.getApiServices().postLogin(loginBody)
            .enqueue(object : Callback<LoginItem> {
                override fun onResponse(call: Call<LoginItem>, response: Response<LoginItem>) {
                    val loginResponse = response.body()

                    if (loginResponse?.status.equals("success") && loginResponse != null) {
                        loginResponse.accountToken?.let { sessionManager.saveJwtToken(it) }
                        goToDashboard(loginResponse.username)
                    }
                }

                override fun onFailure(call: Call<LoginItem>, t: Throwable) {
                    Log.d("testX", "Failure: ${t.localizedMessage}")
                }

            })
    }

}