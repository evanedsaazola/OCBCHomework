package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanedsaazola.ocbchomework.data.model.TransactionResponseItem
import com.evanedsaazola.ocbchomework.databinding.ItemTransactionHistoryBinding
import com.evanedsaazola.ocbchomework.utils.getReadableDate
import java.util.*

class TransactionListAdapter(
    context: Context,
    transactionList: Map<Date, List<TransactionResponseItem>>
) :
    RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    private var _binding: ItemTransactionHistoryBinding? = null
    private val binding get() = _binding!!

    private var transactionData: Map<Date, List<TransactionResponseItem>> = transactionList
    private var orderedData: List<Date> = transactionData.keys.sortedByDescending { it }

    private val listContext: Context = context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionListAdapter.ViewHolder {
        _binding = ItemTransactionHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionListAdapter.ViewHolder, position: Int) {
        val sortedData = orderedData[position]
        val sortedTransactionData = transactionData[sortedData]

        let {
            binding.tvDate.text = getReadableDate(sortedData)
            binding.rvSubListTransactionHistory.layoutManager =
                LinearLayoutManager(binding.rvSubListTransactionHistory.context)
            binding.rvSubListTransactionHistory.adapter =
                TransactionSubListAdapter(listContext, sortedTransactionData!!)
        }
    }

    override fun getItemCount(): Int {
        return orderedData.size
    }

    inner class ViewHolder(binding: ItemTransactionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}