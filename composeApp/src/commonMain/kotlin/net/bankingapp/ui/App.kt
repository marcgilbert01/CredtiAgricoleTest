package net.bankingapp.ui

import androidx.compose.runtime.Composable
import net.bankingapp.ui.common.withViewModelStoreOwner
import net.bankingapp.ui.mainScreen.MainScreen

@Composable
fun App() = withViewModelStoreOwner {
    MainScreen()
}