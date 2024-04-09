package net.bankingapp.ui.accounts

import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.ui.common.UiState

sealed class AccountsUiState : UiState {
    object Loading : AccountsUiState()
    data class DisplayingAccounts(
        val mainBank: List<Bank>,
        val otherBanks: List<Bank>
    ) : AccountsUiState()
}