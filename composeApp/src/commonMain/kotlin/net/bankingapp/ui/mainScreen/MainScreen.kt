package net.bankingapp.ui.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import net.bankingapp.ui.accountDetail.AccountDetailScreen
import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsAction
import net.bankingapp.ui.accounts.AccountsScreen
import net.bankingapp.ui.accounts.AccountsViewModel

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    accountsViewModel: AccountsViewModel,
    accountDetailViewModel: AccountDetailViewModel
) {

    val mainScreenUiState: State<MainScreenUiState> = mainScreenViewModel.uiState.collectAsState(MainScreenUiState.Accounts)
    val event: (MainScreenEvent) -> Unit = { mainScreenViewModel.handleEvent(it) }

    LaunchedEffect(accountsViewModel.action) {
        accountsViewModel.action.collect{
            when(it){
                is AccountsAction.NavigateToAccountDetails -> {
                    event(MainScreenEvent.OnNavigateToAccountDetail(it.accountId))
                }
            }
        }
    }

    when (val uiState = mainScreenUiState.value) {
        MainScreenUiState.Accounts -> {
            AccountsScreen(accountsViewModel)
        }

        is MainScreenUiState.AccountDetails -> {
            AccountDetailScreen(accountDetailViewModel, uiState.accountId)
        }
    }
}