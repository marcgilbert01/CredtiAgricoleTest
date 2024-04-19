package net.bankingapp.ui.mainScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bankingapp.ui.common.Action
import net.bankingapp.ui.common.BaseViewModel

class MainScreenViewModel : BaseViewModel<MainScreenEvent, MainScreenUiState, Action>() {

    init {
        setInitialState(MainScreenUiState.Accounts)
    }

    override fun handleEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is MainScreenEvent.OnNavigateToAccountDetail -> {
                    setUiState {
                        MainScreenUiState.AccountDetails(event.accountId)
                    }
                }

                MainScreenEvent.OnNavigateToMyAccounts -> {
                    setUiState {
                        MainScreenUiState.Accounts
                    }
                }
            }
        }
    }
}