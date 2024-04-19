package net.bankingapp.ui.accountDetail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bankingapp.domain.accounts.usecase.GetAccountUseCase
import net.bankingapp.ui.common.BaseViewModel

class AccountDetailViewModel(
    private val getAccountUseCase: GetAccountUseCase
) : BaseViewModel<AccountDetailEvent, AccountDetailUiState, AccountDetailAction>() {

    init {
        setInitialState(AccountDetailUiState.Loading)
    }

    override fun handleEvent(event: AccountDetailEvent) {
        viewModelScope.launch {
            when (event) {
                AccountDetailEvent.OnUpButtonClicked -> {
                    sendAction { AccountDetailAction.NavigateToMyAccounts }
                }
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