package net.bankingapp.ui.accountDetail

import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.ui.common.UiState

sealed class AccountDetailUiState: UiState {

    object Loading : AccountDetailUiState()

    data class DisplayingAccountDetail(val account: Account) : AccountDetailUiState()

    data class Error(val message: String) : AccountDetailUiState()

}