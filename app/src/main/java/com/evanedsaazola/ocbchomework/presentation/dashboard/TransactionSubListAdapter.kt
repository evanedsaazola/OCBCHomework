package com.evanedsaazola.ocbchomework.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evanedsaazola.ocbchomework.data.model.TransactionResponseItem
import com.evanedsaazola.ocbchomework.databinding.ItemSubTransactionHistoryBinding

class TransactionSubListAdapter(transactionSubList: List<TransactionResponseItem>) :
    RecyclerView.Adapter<TransactionSubListAdapter.ViewHolder>() {

    private var _binding: ItemSubTransactionHistoryBinding? = null
    private val binding get() = _binding!!

    var transactionSubData: List<TransactionResponseItem> = transactionSubList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        _binding = ItemSubTransactionHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionSubData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transactionData = transactionSubData[position]

        if (transactionData.transactionType == "transfer") {
            binding.tvPayerName.text =
                transactionData.transactionReceipient?.receipientAccountHolder
            binding.tvPayerAccount.text = transactionData.transactionReceipient?.receipientAccountNo
            binding.tvTransactionAmount.text = transactionData.amount.toString()
        } else {
            binding.tvPayerName.text = transactionData.transactionSender?.senderAccountHolder
            binding.tvPayerAccount.text = transactionData.transactionSender?.senderAccountNo
            binding.tvTransactionAmount.text = transactionData.amount.toString()
        }
    }

    inner class ViewHolder(binding: ItemSubTransactionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}