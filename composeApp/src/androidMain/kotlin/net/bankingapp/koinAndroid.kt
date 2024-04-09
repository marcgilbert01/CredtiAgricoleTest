package net.bankingapp

import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinAndroid = module {

    //////////////////////////////////////////////////////////////////
    // view models
    viewModel {
        MainScreenViewModel()
    }
    viewModel {
        AccountsViewModel(get())
    }
    viewModel {
        AccountDetailViewModel(get())
    }
}