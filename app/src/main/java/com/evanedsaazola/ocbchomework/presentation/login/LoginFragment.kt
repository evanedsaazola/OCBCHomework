package com.evanedsaazola.ocbchomework.presentation.login

import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val menuNavController: NavController? by lazy { activity?.findNavController(R.id.fragmentContainerView) }
    private lateinit var sessionManager: SessionManager
    private lateinit var networkClientInstance: NetworkClientInstance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            submitLogin()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToDashboard() {
        val directions = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        menuNavController?.navigate(directions)
    }

    private fun submitLogin() {
        val username = binding.etLoginUsername.editText?.text.toString()
        val password = binding.etLoginPassword.editText?.text.toString()

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
                        goToDashboard()
                    }
                }

                override fun onFailure(call: Call<LoginItem>, t: Throwable) {
                    Log.d("testX", "Failure: ${t.localizedMessage}")
                }

            })
    }

}