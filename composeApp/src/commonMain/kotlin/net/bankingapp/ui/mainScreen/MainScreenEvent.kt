package net.bankingapp.ui.mainScreen

import net.bankingapp.ui.common.Event

sealed interface MainScreenEvent: Event {

    data class OnNavigateToAccountDetail(val accountId: String): MainScreenEvent

    data object OnNavigateToMyAccounts: MainScreenEvent
}