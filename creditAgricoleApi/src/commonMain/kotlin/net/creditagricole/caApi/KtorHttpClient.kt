package net.creditagricole.caApi

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val ktorHttpClient = HttpClient() {
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = false
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = false
            }
        )
    }
    expectSuccess = true
}