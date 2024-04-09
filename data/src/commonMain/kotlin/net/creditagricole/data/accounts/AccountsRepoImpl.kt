package net.creditagricole.data.accounts

import net.creditagricole.data.dataSources.AccountsDataSource
import net.creditagricole.domain.accounts.entities.Bank
import net.creditagricole.domain.accounts.repo.AccountsRepo

class AccountsRepoImpl(
    private val accountsDataSource: AccountsDataSource
) : AccountsRepo {
    override suspend fun getAccountsForMainBank(): List<Bank> {
        return accountsDataSource.getAccounts().filter { it.isMainBank }
    }

    override suspend fun getAccountsForOtherBanks(): List<Bank> {
        return accountsDataSource.getAccounts().filter { !it.isMainBank }
    }
}