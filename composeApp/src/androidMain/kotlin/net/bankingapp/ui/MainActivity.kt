package net.bankingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.bankingapp.ui.accounts.AccountsScreen
import net.bankingapp.ui.accounts.AccountsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val accountsViewModel: AccountsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountsScreen(accountsViewModel)
        }
    }
}