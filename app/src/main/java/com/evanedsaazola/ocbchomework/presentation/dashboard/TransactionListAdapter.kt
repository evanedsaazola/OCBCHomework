package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanedsaazola.ocbchomework.data.model.TransactionResponseItem
import com.evanedsaazola.ocbchomework.databinding.ItemTransactionHistoryBinding
import com.evanedsaazola.ocbchomework.getReadableDate

class TransactionListAdapter(transactionList: List<TransactionResponseItem>) :
    RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    private var _binding: ItemTransactionHistoryBinding? = null
    private val binding get() = _binding!!

    private var transactionData: List<TransactionResponseItem> = transactionList

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
        val transactionDataX = transactionData[position]

        let {
            binding.tvDate.text = transactionDataX.transactionDate.getReadableDate()
            binding.rvSubListTransactionHistory.layoutManager =
                LinearLayoutManager(binding.rvSubListTransactionHistory.context)
            binding.rvSubListTransactionHistory.adapter =
                TransactionSubListAdapter(transactionData)
        }

    }

    override fun getItemCount(): Int {
        return transactionData.size
    }

    inner class ViewHolder(binding: ItemTransactionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}