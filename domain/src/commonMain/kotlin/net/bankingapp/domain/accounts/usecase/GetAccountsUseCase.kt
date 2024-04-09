package net.bankingapp.domain.accounts.usecase

import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.domain.accounts.repo.AccountsRepo
import net.bankingapp.domain.common.UseCaseCo

class GetAccountsUseCase(
    private val accountsRepo: AccountsRepo
) : UseCaseCo<List<Bank>, GetAccountsUseCase.Params> {

    data class Params(
        val bankType: BankType
    )

    override suspend fun exe(params: Params): List<Bank> {
        return when (params.bankType) {
            BankType.MAIN_BANK -> accountsRepo.getAccountsForMainBank()
            BankType.OTHER_BANKS -> accountsRepo.getAccountsForOtherBanks()
        }
    }

    enum class BankType {
        MAIN_BANK,
        OTHER_BANKS
    }

}