import androidx.compose.ui.window.ComposeUIViewController
import net.bankingapp.koinCommon
import net.bankingapp.ui.ViewModelsInstances
import net.bankingapp.ui.koinIos
import net.bankingapp.ui.mainScreen.MainScreen
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

private val viewModelsInstances  = ViewModelsInstances()

fun MainViewController(): UIViewController {
    startKoin {
        modules(koinIos, koinCommon)
    }
    return ComposeUIViewController {
        MainScreen(
            mainScreenViewModel = viewModelsInstances.mainScreenViewModel,
            accountsViewModel = viewModelsInstances.accountsViewModel,
            accountDetailViewModel = viewModelsInstances.accountDetailViewModel
        )
    }
}