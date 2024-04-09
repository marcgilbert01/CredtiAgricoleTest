package net.bankingapp.domain.accounts.entities

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: Long,
)
