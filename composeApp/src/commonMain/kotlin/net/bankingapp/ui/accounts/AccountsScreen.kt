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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import net.bankingapp.designSystem.tokens.caColors
import net.bankingapp.designSystem.tokens.caSpacing
import net.bankingapp.designSystem.tokens.caTypography
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.ui.common.formatAmount
import androidx.compose.runtime.State
import net.bankingapp.ui.common.LoadingScreen

@Composable
fun AccountsScreen(
    viewModel: AccountsViewModel
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

@Composable
private fun rememberChevronRight(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "chevron_right",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(14.417f, 29.167f)
                quadToRelative(-0.5f, -0.542f, -0.5f, -1.25f)
                quadToRelative(0f, -0.709f, 0.5f, -1.209f)
                lineToRelative(6.75f, -6.75f)
                lineToRelative(-6.75f, -6.75f)
                quadToRelative(-0.5f, -0.5f, -0.5f, -1.25f)
                reflectiveQuadToRelative(0.5f, -1.25f)
                quadToRelative(0.583f, -0.541f, 1.271f, -0.52f)
                quadToRelative(0.687f, 0.02f, 1.229f, 0.52f)
                lineToRelative(8f, 8.042f)
                quadToRelative(0.25f, 0.25f, 0.395f, 0.562f)
                quadToRelative(0.146f, 0.313f, 0.146f, 0.646f)
                quadToRelative(0f, 0.375f, -0.146f, 0.688f)
                quadToRelative(-0.145f, 0.312f, -0.395f, 0.562f)
                lineToRelative(-7.959f, 8f)
                quadToRelative(-0.541f, 0.5f, -1.25f, 0.5f)
                quadToRelative(-0.708f, 0f, -1.291f, -0.541f)
                close()
            }
        }.build()
    }
}