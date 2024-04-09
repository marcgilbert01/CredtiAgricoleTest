package net.creditagricole.domain.accounts.entities

data class Bank (
    val id: String = "",
    val name: String,
    val balanceTotal: Double,
    val isMainBank: Boolean,
    val accounts: List<Account>
)