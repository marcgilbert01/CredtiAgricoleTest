package net.bankingapp.ui.accounts

import net.bankingapp.ui.common.Event

sealed class AccountsEvent: Event {

    data class OnAccountClicked(val accountId: String) : AccountsEvent()
}