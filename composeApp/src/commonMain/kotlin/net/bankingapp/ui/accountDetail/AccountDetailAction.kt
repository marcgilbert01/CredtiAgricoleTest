package net.bankingapp.ui.accountDetail

import net.bankingapp.ui.common.Action

sealed class AccountDetailAction: Action {

    data object NavigateToMyAccounts : AccountDetailAction()

}