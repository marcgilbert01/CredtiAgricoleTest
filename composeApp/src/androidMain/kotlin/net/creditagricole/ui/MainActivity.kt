package net.creditagricole.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.creditagricole.domain.accounts.entities.Account
import net.creditagricole.domain.accounts.entities.Bank
import net.creditagricole.ui.accounts.AccountsScreen
import net.creditagricole.ui.accounts.AccountsUiState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountsScreen(
                uiState = AccountsUiState.DisplayingAccounts(
                    creditAgricole = listOf(
                        Bank(
                            id = "1",
                            name = "Credit Agricole",
                            balanceTotal = 6000.0,
                            accounts = listOf(
                                Account(
                                    id = "1",
                                    name = "Compte Courant",
                                    balance = 1000.0
                                ),
                                Account(
                                    id = "2",
                                    name = "Livret A",
                                    balance = 5000.0
                                )
                            )
                        ),
                    ),
                    otherBanks = listOf(
                        Bank(
                            id = "2",
                            name = "BNP",
                            balanceTotal = 5000.0,
                            accounts = listOf(
                                Account(
                                    id = "3",
                                    name = "Compte Courant",
                                    balance = 2000.0
                                ),
                                Account(
                                    id = "4",
                                    name = "Livret A",
                                    balance = 3000.0
                                )
                            )
                        ),
                        Bank(
                            id = "3",
                            name = "Societe Generale",
                            balanceTotal = 8000.0,
                            accounts = listOf(
                                Account(
                                    id = "5",
                                    name = "Compte Courant",
                                    balance = 4000.0
                                ),
                                Account(
                                    id = "6",
                                    name = "Livret A",
                                    balance = 5000.0
                                )
                            )
                        )
                    )
                )
            )
        }
    }

}