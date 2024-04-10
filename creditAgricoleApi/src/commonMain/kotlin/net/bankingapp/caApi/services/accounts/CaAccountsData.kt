package net.bankingapp.caApi.services.accounts

import kotlinx.serialization.Serializable

@Serializable
data class CaBankData(
    val accounts: List<CaAccountData>,
    val isCA: Int,
    val name: String
)
@Serializable
data class CaAccountData (
    val balance: Double,
    val contract_number: String,
    val holder: String,
    val id: String,
    val label: String,
    val operations: List<CaOperationData>,
    val order: Int,
    val product_code: String,
    val role: Int
)
@Serializable
data class CaOperationData (
    val amount: String,
    val category: String,
    val date: String,
    val id: String,
    val title: String
)