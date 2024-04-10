package net.bankingapp.ui

import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewModelsInstances : KoinComponent {
    val mainScreenViewModel: MainScreenViewModel by inject()
    val accountsViewModel: AccountsViewModel by inject()
    val accountDetailViewModel: AccountDetailViewModel by inject()
}