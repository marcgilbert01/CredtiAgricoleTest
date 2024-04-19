import androidx.compose.ui.window.ComposeUIViewController
import net.bankingapp.koinCommon
import net.bankingapp.ui.App
import net.bankingapp.ui.common.koinInstance
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        modules(koinCommon)
    }.let {
        koinInstance = it.koin
    }
    return ComposeUIViewController {
        App()
    }
}