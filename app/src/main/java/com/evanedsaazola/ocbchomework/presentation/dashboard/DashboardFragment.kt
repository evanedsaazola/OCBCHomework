package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanedsaazola.ocbchomework.NetworkClientInstance
import com.evanedsaazola.ocbchomework.data.model.BalanceItem
import com.evanedsaazola.ocbchomework.data.model.TransactionsItem
import com.evanedsaazola.ocbchomework.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var networkClientInstance: NetworkClientInstance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        getBalance()
        getTransactions()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getBalance() {
        networkClientInstance = NetworkClientInstance(requireContext())

        networkClientInstance.getApiServices().getBalance().enqueue(object : Callback<BalanceItem> {
            override fun onResponse(call: Call<BalanceItem>, response: Response<BalanceItem>) {
                val responseResult = response.body()
                Log.d("testX", responseResult.toString())
                tvBalance.text = responseResult?.balance.toString()
                tvAccountNum.text = responseResult?.accountNo
            }

            override fun onFailure(call: Call<BalanceItem>, t: Throwable) {
                Log.d("testX", "Failure (on Balance): ${t.localizedMessage}")
            }

        })
    }

    private fun getTransactions() {
        networkClientInstance = NetworkClientInstance(requireContext())

        networkClientInstance.getApiServices().getTransactions()
            .enqueue(object : Callback<TransactionsItem> {
                override fun onResponse(
                    call: Call<TransactionsItem>,
                    response: Response<TransactionsItem>
                ) {
                    val responseResult = response.body()?.dataResponse

                    if (responseResult != null) {
                        Log.d("testX", responseResult.toString())
                        binding.rvTransactionHistoru.layoutManager =
                            LinearLayoutManager(binding.rvTransactionHistoru.context)
                        binding.rvTransactionHistoru.adapter =
                            TransactionListAdapter(responseResult)
                    }
                }

                override fun onFailure(call: Call<TransactionsItem>, t: Throwable) {
                    Log.d("testX", "Failure (on Transactions): ${t.localizedMessage}")
                }

            })
    }
}