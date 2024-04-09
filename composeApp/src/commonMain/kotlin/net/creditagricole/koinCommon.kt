package net.creditagricole

import net.creditagricole.caApi.services.accounts.CaAccountsService
import net.creditagricole.data.accounts.AccountsRepoImpl
import net.creditagricole.data.dataSources.AccountsDataSource
import net.creditagricole.data.dataSources.AccountsDataSourceFromCaApi
import net.creditagricole.domain.accounts.repo.AccountsRepo
import net.creditagricole.domain.accounts.usecase.GetAccountsUseCase
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