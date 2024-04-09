package net.bankingapp.caApi.services.accounts

import io.ktor.client.call.body
import io.ktor.client.request.get
import net.bankingapp.caApi.CA_API_URL
import net.bankingapp.caApi.ktorHttpClient

class CaAccountsService(
    private val url: String = CA_API_URL
) {
    suspend fun getAccounts(): List<CaBankData> {
        return ktorHttpClient.get(url).body()
    }
}