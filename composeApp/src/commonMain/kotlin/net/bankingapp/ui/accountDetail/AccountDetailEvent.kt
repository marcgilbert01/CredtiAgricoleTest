package net.bankingapp.ui.accountDetail

import net.bankingapp.ui.common.Event

sealed class AccountDetailEvent: Event {

    data class OnScreenLoad(val accountId: String) : AccountDetailEvent()

    data object OnUpButtonClicked : AccountDetailEvent()
}