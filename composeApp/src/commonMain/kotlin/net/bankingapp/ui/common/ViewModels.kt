package net.bankingapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import net.bankingapp.domain.accounts.usecase.GetAccountUseCase
import net.bankingapp.domain.accounts.usecase.GetAccountsUseCase
import net.bankingapp.ui.accountDetail.AccountDetailViewModel
import net.bankingapp.ui.accounts.AccountsViewModel
import net.bankingapp.ui.mainScreen.MainScreenViewModel
import org.koin.core.Koin
import kotlin.reflect.KClass

var koinInstance: Koin? = null

val VIEW_MODEL_FACTORY = viewModelFactory {
    initializer { MainScreenViewModel() }
    initializer {
        koinInstance!!.inject<AccountsViewModel>().value
    }
    initializer {
        koinInstance!!.inject<AccountDetailViewModel>().value
    }
}

@Composable
internal fun <VM : ViewModel> viewModel(
    modelClass: KClass<VM>
): VM = androidx.lifecycle.viewmodel.compose.viewModel(modelClass, factory = VIEW_MODEL_FACTORY)

private class ComposeViewModelStoreOwner: ViewModelStoreOwner {
    override val viewModelStore = ViewModelStore()
    fun dispose() { viewModelStore.clear() }
}

@Composable
private fun rememberComposeViewModelStoreOwner(): ViewModelStoreOwner {
    val viewModelStoreOwner = remember { ComposeViewModelStoreOwner() }
    DisposableEffect(viewModelStoreOwner) {
        onDispose { viewModelStoreOwner.dispose() }
    }
    return viewModelStoreOwner
}

@Composable
internal fun withViewModelStoreOwner(content: @Composable () -> Unit) {
    if (LocalViewModelStoreOwner.current != null) {
        // Normal case: use system-provided owner
        content()
    } else {
        // Fallback case: use ViewModelStoreOwner with scope of this composable.
        // It's required for Compose Multiplatform for now because it's not providing default value yet.
        // Expected to be fixed in Compose Multiplatform 1.7.0
        CompositionLocalProvider(
            LocalViewModelStoreOwner provides rememberComposeViewModelStoreOwner(),
            content = content
        )
    }
}