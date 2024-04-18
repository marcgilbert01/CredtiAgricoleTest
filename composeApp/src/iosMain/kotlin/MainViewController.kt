import androidx.compose.ui.window.ComposeUIViewController
import net.bankingapp.koinCommon
import net.bankingapp.ui.App
import net.bankingapp.ui.ViewModelsInstances
import net.bankingapp.ui.common.koinInstance
import net.bankingapp.ui.koinIos
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

private val viewModelsInstances  = ViewModelsInstances()

fun MainViewController(): UIViewController {
    startKoin {
        modules(koinIos, koinCommon)
    }.let {
        koinInstance = it.koin
    }
    return ComposeUIViewController {
        App()
    }
}