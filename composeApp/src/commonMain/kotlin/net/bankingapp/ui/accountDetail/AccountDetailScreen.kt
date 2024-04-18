package net.bankingapp.ui.accountDetail

import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import net.bankingapp.designSystem.atoms.UpButton
import net.bankingapp.designSystem.tokens.caSpacing
import net.bankingapp.designSystem.tokens.caTypography
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.entities.Transaction
import net.bankingapp.ui.common.ErrorScreen
import net.bankingapp.ui.common.LoadingScreen
import net.bankingapp.ui.common.formatAmount
import net.bankingapp.ui.common.viewModel
import net.bankingapp.ui.mainScreen.MainScreenEvent
import net.bankingapp.utils.convertToDate

@Composable
fun AccountDetailScreen(
    viewModel: AccountDetailViewModel = viewModel(AccountDetailViewModel::class),
    accountId: String,
    mainScreenEvent: (MainScreenEvent) -> Unit
) {

    val accountDetailUiState: State<AccountDetailUiState> = viewModel.uiState.collectAsState(AccountDetailUiState.Loading)
    val event: (AccountDetailEvent) -> Unit = { viewModel.handleEvent(it) }
    LaunchedEffect(key1 = accountId) {
        event(AccountDetailEvent.OnScreenLoad(accountId))
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val uiState = accountDetailUiState.value) {
            is AccountDetailUiState.Loading -> {
                LoadingScreen()
            }
            is AccountDetailUiState.Error -> {
                ErrorScreen(uiState.message)
            }
            is AccountDetailUiState.DisplayingAccountDetail -> {
                AccountDetailView(uiState.account, event)
            }
        }
    }
    LaunchedEffect(viewModel.action) {
        viewModel.action.collect{
            when(it){
                is AccountDetailAction.NavigateToMyAccounts -> {
                    mainScreenEvent(MainScreenEvent.OnNavigateToMyAccounts)
                }
            }
        }
    }
}

@Composable
fun AccountDetailView(account: Account, event: (AccountDetailEvent) -> Unit) {
    Column {
        UpButton("Mes Comptes") {
            event(AccountDetailEvent.OnUpButtonClicked)
        }
        LazyColumn {
            item {
                Text(
                    text = account.balance.formatAmount(),
                    style = caTypography.h1,
                    modifier = Modifier
                        .padding(caSpacing.screenHorizontalMargin)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                Text(
                    text = account.name,
                    style = caTypography.h2,
                    modifier = Modifier
                        .padding(caSpacing.screenHorizontalMargin)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            items(account.transactions) { transaction ->
                TransactionItemView(transaction)
            }
        }
    }
}

@Composable
fun TransactionItemView(transaction: Transaction) {
    Row(
        horizontalArrangement = SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(caSpacing.listItemHeight)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(start = caSpacing.screenHorizontalMargin),
            verticalArrangement = SpaceEvenly
        ) {
            Text(text = transaction.title, style = caTypography.body1)
            Text(text = transaction.date.convertToDate(), style = caTypography.body1, modifier = Modifier.padding(start = caSpacing.screenHorizontalMargin))
        }
        Text(text = transaction.amount,
            style = caTypography.body2,
            modifier = Modifier.padding(end = caSpacing.screenHorizontalMargin)
        )
    }
}
