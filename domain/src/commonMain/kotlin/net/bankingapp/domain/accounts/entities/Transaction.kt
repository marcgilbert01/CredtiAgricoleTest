package net.bankingapp.domain.accounts.entities

data class Transaction(
    val id: String,
    val title: String,
    val amount: String,
    val date: Long,
)
