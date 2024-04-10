package net.bankingapp.ui

import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.dsl.module

val koinIos = module {

    // view models
    factory {
        MainScreenViewModel()
    }

    factory {
        AccountsViewModel(get())
    }

    factory {
        AccountDetailViewModel(get())
    }
}