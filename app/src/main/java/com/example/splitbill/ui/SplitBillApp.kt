package com.example.splitbill.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splitbill.ui.navigation.Screen
import com.example.splitbill.ui.screens.AddExpenseScreen
import com.example.splitbill.ui.screens.DashboardScreen
import com.example.splitbill.ui.screens.PeopleScreen
import androidx.navigation.navArgument
import androidx.navigation.NavType

@Composable
fun SplitBillApp() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(navController)
            }
            composable(Screen.People.route) {
                PeopleScreen(navController)
            }
            composable(
                route = Screen.AddExpense.route + "?expenseId={expenseId}",
                arguments = listOf(
                    navArgument("expenseId") {
                        type = NavType.LongType
                        defaultValue = -1L
                    }
                )
            ) { backStackEntry ->
                val expenseId = backStackEntry.arguments?.getLong("expenseId") ?: -1L
                AddExpenseScreen(navController, expenseId = expenseId)
            }
        }
    }
}
