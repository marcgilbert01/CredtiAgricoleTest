package net.bankingapp.ui.mainScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.bankingapp.ui.accountDetail.AccountDetailScreen
import net.bankingapp.ui.accounts.AccountsScreen

enum class Screens {
    Accounts,
    AccountDetails
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Accounts.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screens.Accounts.name) {
            AccountsScreen(navHostController = navController)
        }
        composable(
            route = "${Screens.AccountDetails.name}/{accountId}",
            arguments = listOf(navArgument("accountId") {
                type = NavType.StringType
                nullable = true
            })
        ) {navBackStackEntry ->
            AccountDetailScreen(
                accountId = navBackStackEntry.arguments?.getString("accountId")!!,
                navHostController = navController
            )
        }
    }
}