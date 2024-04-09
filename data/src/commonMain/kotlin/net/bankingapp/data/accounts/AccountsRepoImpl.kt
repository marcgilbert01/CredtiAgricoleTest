package net.bankingapp.data.accounts

import net.bankingapp.data.dataSources.AccountsDataSource
import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.domain.accounts.repo.AccountsRepo

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