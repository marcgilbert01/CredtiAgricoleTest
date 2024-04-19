package net.bankingapp

import net.bankingapp.caApi.services.accounts.CaAccountsService
import net.bankingapp.data.accounts.AccountsRepoImpl
import net.bankingapp.data.dataSources.AccountsDataSource
import net.bankingapp.data.dataSources.AccountsDataSourceFromCaApi
import net.bankingapp.domain.accounts.repo.AccountsRepo
import net.bankingapp.domain.accounts.usecase.GetAccountUseCase
import net.bankingapp.domain.accounts.usecase.GetAccountsUseCase
import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.dsl.module

val koinCommon = module {

    //////////////////////////////////////////////////////////////////
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


    //////////////////////////////////////////////////////////////////
    // use cases
    factory {
        GetAccountsUseCase(get())
    }
    factory {
        GetAccountUseCase(get())
    }

    //////////////////////////////////////////////////////////////////
    // repo
    single<AccountsRepo> {
        AccountsRepoImpl(get())
    }

    //////////////////////////////////////////////////////////////////
    // data sources
    single<AccountsDataSource> {
        AccountsDataSourceFromCaApi(get())
    }

    //////////////////////////////////////////////////////////////////
    // services
    single {
        CaAccountsService()
    }

}