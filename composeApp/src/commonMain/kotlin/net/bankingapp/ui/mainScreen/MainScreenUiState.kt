package net.bankingapp.ui.mainScreen

import net.bankingapp.ui.common.UiState

sealed interface MainScreenUiState : UiState {

    data object Accounts : MainScreenUiState

    data class AccountDetails(val accountId: String) : MainScreenUiState
}