package net.bankingapp.ui.accountDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.ui.common.ErrorScreen
import net.bankingapp.ui.common.LoadingScreen

@Composable
fun AccountDetailScreen(
    viewModel: AccountDetailViewModel,
    accountId: String
) {

    val accountDetailUiState: State<AccountDetailUiState> = viewModel.uiState.collectAsState(AccountDetailUiState.Loading)
    val event: (AccountDetailEvent) -> Unit = { viewModel.handleEvent(it) }
    LaunchedEffect(key1 = accountId) {
        event(AccountDetailEvent.OnScreenLoad(accountId))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val uiState = accountDetailUiState.value) {
            is AccountDetailUiState.Loading -> {
                LoadingScreen()
            }
            is AccountDetailUiState.Error -> {
                ErrorScreen(uiState.message)
            }
            is AccountDetailUiState.DisplayingAccountDetail -> {
                AccountDetailView(uiState.account)
            }
        }
    }

}

@Composable
fun AccountDetailView(account: Account) {
    Text("Account Detail View - ${account.name}")
}