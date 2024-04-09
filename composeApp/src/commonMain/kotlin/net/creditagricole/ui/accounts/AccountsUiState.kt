package net.creditagricole.ui.accounts

import net.creditagricole.domain.accounts.entities.Bank
import net.creditagricole.ui.common.UiState

sealed class AccountsUiState : UiState {
    object Loading : AccountsUiState()
    data class DisplayingAccounts(
        val mainBank: List<Bank>,
        val otherBanks: List<Bank>
    ) : AccountsUiState()
}