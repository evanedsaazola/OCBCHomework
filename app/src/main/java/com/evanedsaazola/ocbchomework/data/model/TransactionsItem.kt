package com.evanedsaazola.ocbchomework.data.model

import com.google.gson.annotations.SerializedName

data class TransactionsItem(
    @SerializedName("data")
    val dataResponse: List<TransactionResponseItem>?
) {

}

data class TransactionResponseItem(
    @SerializedName("transactionId")
    val transactionId: String?,

    @SerializedName("amount")
    val amount: Int?,

    @SerializedName("transactionDate")
    val transactionDate: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("transactionType")
    val transactionType: String?,

    @SerializedName("sender")
    val transactionSender: TransactionSenderItem?,

    @SerializedName("receipient")
    val transactionReceipient: TransactionReceipient?
) {

}

data class TransactionSenderItem(
    @SerializedName("accountNo")
    val senderAccountNo: String?,

    @SerializedName("accountHolder")
    val senderAccountHolder: String?
) {

}

data class TransactionReceipient(
    @SerializedName("accountNo")
    val receipientAccountNo: String?,

    @SerializedName("accountHolder")
    val receipientAccountHolder: String?
) {

}