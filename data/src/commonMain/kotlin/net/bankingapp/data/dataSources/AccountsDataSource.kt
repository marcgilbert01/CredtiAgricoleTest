package net.bankingapp.data.dataSources

import net.bankingapp.domain.accounts.entities.Bank

interface AccountsDataSource {
    suspend fun getAccounts(): List<Bank>
}
