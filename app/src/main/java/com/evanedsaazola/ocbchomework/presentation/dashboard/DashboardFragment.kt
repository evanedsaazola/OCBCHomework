package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanedsaazola.ocbchomework.utils.NetworkClientInstance
import com.evanedsaazola.ocbchomework.R
import com.evanedsaazola.ocbchomework.utils.SessionManager
import com.evanedsaazola.ocbchomework.utils.currencyFormatter
import com.evanedsaazola.ocbchomework.data.model.BalanceItem
import com.evanedsaazola.ocbchomework.data.model.TransactionResponseItem
import com.evanedsaazola.ocbchomework.data.model.TransactionsItem
import com.evanedsaazola.ocbchomework.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var networkClientInstance: NetworkClientInstance
    private lateinit var sessionManager: SessionManager
    private val menuNavController: NavController? by lazy { activity?.findNavController(R.id.fragmentContainerView) }
    private val args: DashboardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        getBalance()
        getTransactions()

        binding.tvLogout.setOnClickListener {
            logoutAccount()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logoutAccount() {
        sessionManager = SessionManager(requireContext())
        menuNavController?.navigateUp()
        sessionManager.deleteJwtToken()
        Toast.makeText(requireContext(), "You have logged out. Thank you for using our application", Toast.LENGTH_SHORT).show()
    }

    private fun getBalance() {
        networkClientInstance = NetworkClientInstance(requireContext())

        networkClientInstance.getApiServices().getBalance().enqueue(object : Callback<BalanceItem> {
            override fun onResponse(call: Call<BalanceItem>, response: Response<BalanceItem>) {
                val responseResult = response.body()
                tvBalance.text = currencyFormatter(responseResult?.balance)
                tvAccountNum.text = responseResult?.accountNo
                args.apply {
                    tvAccountHolder.text = accountHolder
                }
            }

            override fun onFailure(call: Call<BalanceItem>, t: Throwable) {
                Log.d("errorApiCall", "Failure (on Balance): ${t.localizedMessage}")
                Toast.makeText(requireContext(), "Unexpected error has happened. Please check your internet connection and try again.", Toast.LENGTH_SHORT).show()
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
                        binding.rvTransactionHistoru.layoutManager =
                            LinearLayoutManager(binding.rvTransactionHistoru.context)
                        binding.rvTransactionHistoru.adapter =
                            initializeData(responseResult)
                    }
                }

                override fun onFailure(call: Call<TransactionsItem>, t: Throwable) {
                    Log.d("errorApiCall", "Failure (on Transactions): ${t.localizedMessage}")
                    Toast.makeText(requireContext(), "Unexpected error has happened. Please check your internet connection and try again.", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun initializeData(responseResult: List<TransactionResponseItem>?): TransactionListAdapter? {

        var transactionListAdapter: TransactionListAdapter? = null

        if (responseResult != null) {
            responseResult.map {
                val calendar = Calendar.getInstance(Locale.US)
                calendar.time = it.transactionDate
                calendar.set(Calendar.HOUR, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.AM_PM, Calendar.AM)
                it.transactionDate = calendar.time
            }
            val sortedResult =
                responseResult.groupBy { it.transactionDate }.toSortedMap(reverseOrder())
            transactionListAdapter = TransactionListAdapter(requireContext(), sortedResult)
        }
        return transactionListAdapter
    }
}