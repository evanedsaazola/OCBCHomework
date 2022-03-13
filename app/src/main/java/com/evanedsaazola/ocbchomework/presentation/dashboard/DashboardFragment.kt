package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evanedsaazola.ocbchomework.NetworkClientInstance
import com.evanedsaazola.ocbchomework.SessionManager
import com.evanedsaazola.ocbchomework.data.model.BalanceItem
import com.evanedsaazola.ocbchomework.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var sessionManager: SessionManager
    private lateinit var networkClientInstance: NetworkClientInstance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        getBalance()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getBalance() {
        networkClientInstance = NetworkClientInstance(requireContext())
        sessionManager = SessionManager(requireContext())

        networkClientInstance.getApiServices().getBalance().enqueue(object : Callback<BalanceItem> {
            override fun onResponse(call: Call<BalanceItem>, response: Response<BalanceItem>) {
                val responseResult = response.body()
                Log.d("testX", responseResult.toString())
                tvBalance.text = responseResult?.balance.toString()
                tvAccountNum.text = responseResult?.accountNo
            }

            override fun onFailure(call: Call<BalanceItem>, t: Throwable) {
                Log.d("testX", "Failure: ${t.localizedMessage}")
            }

        })
    }
}