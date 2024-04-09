package net.bankingapp

import net.bankingapp.caApi.services.accounts.CaAccountsService
import net.bankingapp.data.accounts.AccountsRepoImpl
import net.bankingapp.data.dataSources.AccountsDataSource
import net.bankingapp.data.dataSources.AccountsDataSourceFromCaApi
import net.bankingapp.domain.accounts.repo.AccountsRepo
import net.bankingapp.domain.accounts.usecase.GetAccountsUseCase
import org.koin.dsl.module

val koinCommon = module {

    //////////////////////////////////////////////////////////////////
    // use cases
    factory {
        GetAccountsUseCase(get())
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