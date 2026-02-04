package com.example.splitbill.ui.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object People : Screen("people")
    object AddExpense : Screen("add_expense")
}
