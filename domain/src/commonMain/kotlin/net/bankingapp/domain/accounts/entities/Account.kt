package net.bankingapp.domain.accounts.entities

data class Account(
    val id: String,
    val name: String,
    val balance: Double,
    val transactions: List<Transaction>
)