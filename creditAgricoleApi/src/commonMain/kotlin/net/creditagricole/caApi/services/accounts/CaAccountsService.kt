package net.creditagricole.caApi.services.accounts

import io.ktor.client.call.body
import io.ktor.client.request.get
import net.creditagricole.caApi.CA_API_URL
import net.creditagricole.caApi.ktorHttpClient

class CaAccountsService(
    private val url: String = CA_API_URL
) {
    suspend fun getAccounts(): List<CaBankData> {
        return ktorHttpClient.get(url).body()
    }
}