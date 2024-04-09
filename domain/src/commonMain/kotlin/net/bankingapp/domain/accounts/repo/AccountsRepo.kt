package net.bankingapp.domain.accounts.repo

import net.bankingapp.domain.accounts.entities.Bank

interface AccountsRepo {

    suspend fun getAccountsForMainBank(): List<Bank>

    suspend fun getAccountsForOtherBanks(): List<Bank>
}