package net.bankingapp.ui.accounts

import net.bankingapp.ui.common.Action

sealed class AccountsAction : Action {

    data class NavigateToAccountDetails(val accountId: String) : AccountsAction()
}