package net.bankingapp.ui.accountDetail

import kotlinx.coroutines.launch
import net.bankingapp.domain.accounts.usecase.GetAccountUseCase
import net.bankingapp.ui.common.Action
import net.bankingapp.ui.common.BaseViewModel

class AccountDetailViewModel(
    private val getAccountUseCase: GetAccountUseCase
) : BaseViewModel<AccountDetailEvent, AccountDetailUiState, Action>() {

    init {
        setInitialState(AccountDetailUiState.Loading)
    }

    override fun handleEvent(event: AccountDetailEvent) {
        coroutineScope.launch {
            when (event) {
                AccountDetailEvent.OnBackButtonClicked -> TODO()
                is AccountDetailEvent.OnScreenLoad -> {
                    getAndDisplayAccountDetail(event.accountId)
                }
            }
        }
    }

    private suspend fun getAndDisplayAccountDetail(accountId: String) {
        try {
            getAccountUseCase.exe(GetAccountUseCase.Params(accountId)).let { account ->
                setUiState {
                    AccountDetailUiState.DisplayingAccountDetail(account)
                }
            }
        } catch (e: Exception) {
            setUiState { AccountDetailUiState.Error(e.message!!) }
        }
    }
}