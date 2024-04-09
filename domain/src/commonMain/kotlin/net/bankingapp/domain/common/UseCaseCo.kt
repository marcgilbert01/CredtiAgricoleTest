package net.bankingapp.domain.common

interface UseCaseCo<out Type, in Params> where Type : Any? {
    suspend fun exe(params: Params): Type
}