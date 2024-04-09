package net.bankingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreen
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainScreenViewModel: MainScreenViewModel by viewModel()
    private val accountsViewModel: AccountsViewModel by viewModel()
    private val accountDetailViewModel: AccountDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                mainScreenViewModel = mainScreenViewModel,
                accountsViewModel = accountsViewModel,
                accountDetailViewModel = accountDetailViewModel
            )
        }
    }
}