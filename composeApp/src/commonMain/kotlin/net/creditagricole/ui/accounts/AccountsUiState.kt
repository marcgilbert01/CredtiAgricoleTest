package net.creditagricole.ui.accounts

import net.creditagricole.domain.accounts.entities.Bank

sealed class AccountsUiState {

    data class DisplayingAccounts(
        val creditAgricole: List<Bank>,
        val otherBanks: List<Bank>
    ) : AccountsUiState()
}