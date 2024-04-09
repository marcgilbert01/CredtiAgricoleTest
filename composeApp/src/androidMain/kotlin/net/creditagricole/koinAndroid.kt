package net.creditagricole

import net.creditagricole.ui.accounts.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val koinAndroid = module {

    //////////////////////////////////////////////////////////////////
    // view models
    viewModel {
        AccountsViewModel(get())
    }
}