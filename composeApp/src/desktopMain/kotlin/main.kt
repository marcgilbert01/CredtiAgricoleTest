import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.bankingapp.koinCommon
import net.bankingapp.koinDesktop
import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreen
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() {
    startKoin {
        modules(koinDesktop, koinCommon)
    }
    val mainScreenViewModel: MainScreenViewModel =
        inject<MainScreenViewModel>(MainScreenViewModel::class.java).value
    val accountsViewModel: AccountsViewModel =
        inject<AccountsViewModel>(AccountsViewModel::class.java).value
    val accountDetailViewModel: AccountDetailViewModel =
        inject<AccountDetailViewModel>(AccountDetailViewModel::class.java).value


    application {
        Window(onCloseRequest = ::exitApplication, title = "Credit Agricole Banking App") {
            MainScreen(
                mainScreenViewModel = mainScreenViewModel,
                accountsViewModel = accountsViewModel,
                accountDetailViewModel = accountDetailViewModel
            )
        }
    }
}