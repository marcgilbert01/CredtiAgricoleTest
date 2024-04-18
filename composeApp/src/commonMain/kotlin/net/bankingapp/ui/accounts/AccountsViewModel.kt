package net.bankingapp.ui.accounts

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.bankingapp.domain.accounts.usecase.GetAccountsUseCase
import net.bankingapp.ui.common.BaseViewModel2

class AccountsViewModel(
    private val getAccountsUseCase: GetAccountsUseCase
) : BaseViewModel2<AccountsEvent, AccountsUiState, AccountsAction>() {

    init {
        setInitialState(AccountsUiState.Loading)
        viewModelScope.launch {
            val mainBankDeferred = async {
                getAccountsUseCase.exe(GetAccountsUseCase.Params(GetAccountsUseCase.BankType.MAIN_BANK))
            }
            val otherBankDeferred = async {
                getAccountsUseCase.exe(GetAccountsUseCase.Params(GetAccountsUseCase.BankType.OTHER_BANKS))
            }
            val mainBank = mainBankDeferred.await()
            val otherBank = otherBankDeferred.await()
            setUiState {
                AccountsUiState.DisplayingAccounts(
                    mainBank = mainBank,
                    otherBanks = otherBank
                )
            }
        }
    }

    override fun handleEvent(event: AccountsEvent) {
        viewModelScope.launch {
            when (event) {
                is AccountsEvent.OnAccountClicked -> {
                    sendAction { AccountsAction.NavigateToAccountDetails(event.accountId) }
                }
            }
        }
    }
}