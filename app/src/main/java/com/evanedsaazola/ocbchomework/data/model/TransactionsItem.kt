package com.evanedsaazola.ocbchomework.data.model

import com.google.gson.annotations.SerializedName

data class TransactionsItem(
    @SerializedName("data")
    val dataResponse: List<TransactionResponseItem>?
)

data class TransactionResponseItem(
    @SerializedName("transactionId")
    val transactionId: String?,

    @SerializedName("amount")
    val amount: Double? = 0.0,

    @SerializedName("transactionDate")
    var transactionDate: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("transactionType")
    val transactionType: String?,

    @SerializedName("sender")
    val transactionSender: TransactionSenderItem?,

    @SerializedName("receipient")
    val transactionReceipient: TransactionReceipientItem?
)

data class TransactionSenderItem(
    @SerializedName("accountNo")
    val senderAccountNo: String?,

    @SerializedName("accountHolder")
    val senderAccountHolder: String?
)

data class TransactionReceipientItem(
    @SerializedName("accountNo")
    val receipientAccountNo: String?,

    @SerializedName("accountHolder")
    val receipientAccountHolder: String?
)