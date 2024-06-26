package net.bankingapp.ui.accounts

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.bankingapp.domain.accounts.usecase.GetAccountsUseCase
import net.bankingapp.ui.common.BaseViewModel

class AccountsViewModel(
    private val getAccountsUseCase: GetAccountsUseCase
) : BaseViewModel<AccountsEvent, AccountsUiState, AccountsAction>() {

    init {
        setInitialState(AccountsUiState.Loading)
        coroutineScope.launch {
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
        coroutineScope.launch {
            when (event) {
                is AccountsEvent.OnAccountClicked -> {
                    sendAction { AccountsAction.NavigateToAccountDetails(event.accountId) }
                }
            }
        }
    }
}