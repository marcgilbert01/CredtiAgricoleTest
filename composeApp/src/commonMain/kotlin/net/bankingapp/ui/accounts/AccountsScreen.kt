package net.bankingapp.ui.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.bankingapp.designSystem.tokens.caColors
import net.bankingapp.designSystem.tokens.caSpacing
import net.bankingapp.designSystem.tokens.caTypography
import net.bankingapp.designSystem.tokens.rememberChevronRight
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.ui.common.LoadingScreen
import net.bankingapp.ui.common.formatAmount
import net.bankingapp.ui.common.viewModel
import net.bankingapp.ui.mainScreen.Screens

@Composable
fun AccountsScreen(
    viewModel: AccountsViewModel = viewModel(AccountsViewModel::class),
    navHostController: NavHostController
) {
    val accountsUiState: State<AccountsUiState> = viewModel.uiState.collectAsState(AccountsUiState.Loading)
    val event: (AccountsEvent) -> Unit = { viewModel.handleEvent(it) }

    when (val uiState = accountsUiState.value) {
        is AccountsUiState.DisplayingAccounts -> {
            displayingAccountsView(uiState, event)
        }
        AccountsUiState.Loading -> {
            LoadingScreen()
        }
    }
    LaunchedEffect(viewModel.action) {
        viewModel.action.collect{
            when(it){
                is AccountsAction.NavigateToAccountDetails -> {
                    navHostController.navigate(route = "${Screens.AccountDetails.name}/${it.accountId}")
                }
            }
        }
    }
}

@Composable
private fun displayingAccountsView(
    uiState: AccountsUiState.DisplayingAccounts,
    event: (AccountsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(caColors.surface)
            .verticalScroll(rememberScrollState(0))
    ) {
        Text(
            text = "Mes Comptes",
            style = caTypography.h1,
            modifier = Modifier.padding(caSpacing.screenHorizontalMargin)
        )
        Text(text = "Credit Agricole",
            style = caTypography.h3,
            modifier = Modifier
                .padding(start = caSpacing.screenHorizontalMargin, top = caSpacing.listVerticalSpacing, bottom = caSpacing.listVerticalSpacing)
                .fillMaxWidth()
        )
        uiState.mainBank.forEach {
            BankMenuItem(
                bank = it,
                onClicked = { accountId ->
                    event(AccountsEvent.OnAccountClicked(accountId))
                }
            )
        }
        Text(text = "Autres Banques",
            style = caTypography.h3,
            modifier = Modifier
                .padding(start = caSpacing.screenHorizontalMargin, top = caSpacing.listVerticalSpacing, bottom = caSpacing.listVerticalSpacing)
                .fillMaxWidth()
        )
        uiState.otherBanks.forEach {
            BankMenuItem(
                bank = it,
                onClicked = { accountId ->
                    event(AccountsEvent.OnAccountClicked(accountId))
                }
            )
        }
    }
}

@Composable
private fun BankMenuItem(
    bank: Bank,
    onClicked: (accountId: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(Modifier
        .fillMaxWidth()
        .background(caColors.background)
        .padding(start = caSpacing.screenHorizontalMargin)
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(caSpacing.listItemHeight),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically,
        ) {
            Text(bank.name, style = caTypography.body1)
            Row(verticalAlignment = CenterVertically) {
                Text(bank.balanceTotal.formatAmount(), style = caTypography.body2)
                Icon(
                    rememberChevronRight(),
                    "expand",
                    tint = caColors.secondary,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .rotate(if (expanded) 270f else 90f)
                        .padding(start = caSpacing.smallSpacing, end = caSpacing.smallSpacing)
                )
            }
        }
        Divider(color = caColors.surface, thickness = 1.dp)
        if (expanded) {
            bank.accounts.forEach {
                AccountMenuItem(
                    account = it,
                    onClicked = onClicked
                )
            }
        }
    }
}

@Composable
private fun AccountMenuItem(
    account: Account,
    onClicked: (accountId: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(start = caSpacing.screenHorizontalMargin)) {
        Row(
            modifier = Modifier.fillMaxWidth().height(caSpacing.listItemHeight),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically,
        ) {
            Text(account.name, style = caTypography.body1)
            Row(verticalAlignment = CenterVertically) {
                Text(account.balance.formatAmount(), style = caTypography.body2)
                Icon(
                    rememberChevronRight(),
                    tint = caColors.secondary,
                    contentDescription = "Show account details",
                    modifier = Modifier
                        .clickable { onClicked(account.id) }
                        .padding(start = caSpacing.smallSpacing, end = caSpacing.smallSpacing),
                )
            }
        }
        Divider(color = caColors.surface, thickness = 1.dp)
    }
}