import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.bankingapp.koinCommon
import net.bankingapp.ui.App
import net.bankingapp.ui.common.koinInstance
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(koinCommon)
    }.let {
        koinInstance = it.koin
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "Credit Agricole Banking App") {
            App()
        }
    }
}