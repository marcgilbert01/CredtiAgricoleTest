package net.creditagricole.data.dataSources

import net.creditagricole.caApi.services.accounts.CaAccountData
import net.creditagricole.caApi.services.accounts.CaAccountsService
import net.creditagricole.caApi.services.accounts.CaBankData
import net.creditagricole.domain.accounts.entities.Account
import net.creditagricole.domain.accounts.entities.Bank

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