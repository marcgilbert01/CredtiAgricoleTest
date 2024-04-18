package net.bankingapp.ui.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import net.bankingapp.ui.accountDetail.AccountDetailScreen
import net.bankingapp.ui.accounts.AccountsScreen
import net.bankingapp.ui.common.viewModel

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = viewModel(MainScreenViewModel::class)
) {

    val mainScreenUiState: State<MainScreenUiState> = mainScreenViewModel.uiState.collectAsState(MainScreenUiState.Accounts)
    val event: (MainScreenEvent) -> Unit = { mainScreenViewModel.handleEvent(it) }

    when (val uiState = mainScreenUiState.value) {
        MainScreenUiState.Accounts -> {
            AccountsScreen(mainScreenEvent = event)
        }

        is MainScreenUiState.AccountDetails -> {
            AccountDetailScreen(accountId =  uiState.accountId, mainScreenEvent = event)
        }
    }
}