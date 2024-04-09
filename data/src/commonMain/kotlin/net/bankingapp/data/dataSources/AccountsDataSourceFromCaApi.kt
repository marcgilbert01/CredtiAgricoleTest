package net.bankingapp.data.dataSources

import net.bankingapp.caApi.services.accounts.CaAccountData
import net.bankingapp.caApi.services.accounts.CaAccountsService
import net.bankingapp.caApi.services.accounts.CaBankData
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.entities.Bank

class AccountsDataSourceFromCaApi(
    private val caAccountsService: CaAccountsService
): AccountsDataSource {

    override suspend fun getAccounts(): List<Bank> {
        return caAccountsService.getAccounts().map {
            it.toDomain()
        }
    }
}

fun CaBankData.toDomain() = Bank(
    id = this.name,
    name = this.name,
    accounts = this.accounts.map { it.toDomain() },
    balanceTotal = this.accounts.sumOf { it.balance },
    isMainBank = this.isCA > 0
)

fun CaAccountData.toDomain() = Account(
    id = this.id,
    name = this.label,
    balance = this.balance,
)