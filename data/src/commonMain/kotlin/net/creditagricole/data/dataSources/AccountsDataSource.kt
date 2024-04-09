package net.creditagricole.data.dataSources

import net.creditagricole.domain.accounts.entities.Bank

interface AccountsDataSource {
    suspend fun getAccounts(): List<Bank>
}
