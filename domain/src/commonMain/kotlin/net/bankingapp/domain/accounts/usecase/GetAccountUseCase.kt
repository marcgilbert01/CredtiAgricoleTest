package net.bankingapp.domain.accounts.usecase

import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.repo.AccountsRepo
import net.bankingapp.domain.common.UseCaseCo

class GetAccountUseCase(
    private val accountsRepo: AccountsRepo
) : UseCaseCo<Account, GetAccountUseCase.Params> {

    data class Params(
        val accountId: String
    )

    override suspend fun exe(params: Params): Account {
        val mainBankAccounts = accountsRepo.getAccountsForMainBank()
        val otherBankAccounts = accountsRepo.getAccountsForOtherBanks()
        val allAccounts = mutableListOf<Account>()
        (mainBankAccounts + otherBankAccounts).map {
            allAccounts.addAll(it.accounts)
        }
        return allAccounts.first { it.id == params.accountId }
    }
}