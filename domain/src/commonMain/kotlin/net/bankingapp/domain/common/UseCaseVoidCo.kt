package net.bankingapp.domain.common

interface UseCaseVoidCo<out Type> where Type : Any {
    suspend fun exe(): Type
}