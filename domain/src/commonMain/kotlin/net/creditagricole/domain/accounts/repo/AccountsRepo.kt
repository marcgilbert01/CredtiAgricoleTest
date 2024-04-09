package net.creditagricole.domain.accounts.repo

import net.creditagricole.domain.accounts.entities.Bank

interface AccountsRepo {

    suspend fun getAccountsForMainBank(): List<Bank>

    suspend fun getAccountsForOtherBanks(): List<Bank>
}