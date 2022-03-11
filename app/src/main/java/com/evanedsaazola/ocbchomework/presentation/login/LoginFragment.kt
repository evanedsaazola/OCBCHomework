package com.evanedsaazola.ocbchomework.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.evanedsaazola.ocbchomework.R
import com.evanedsaazola.ocbchomework.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val menuNavController: NavController? by lazy { activity?.findNavController(R.id.fragmentContainerView) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            goToDashboard()
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

}