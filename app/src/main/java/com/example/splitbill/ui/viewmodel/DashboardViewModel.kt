package com.example.splitbill.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.repository.SplitBillRepository
import com.example.splitbill.domain.usecase.GetDebtsUseCase
import com.example.splitbill.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val debts: List<DebtDisplay> = emptyList(),
    val expenses: List<ExpenseDisplay> = emptyList()
)

data class DebtDisplay(
    val fromName: String,
    val toName: String,
    val amount: String
)

data class ExpenseDisplay(
    val id: Long,
    val description: String,
    val amount: String,
    val payerName: String,
    val date: String
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDebtsUseCase: GetDebtsUseCase,
    private val repository: SplitBillRepository,
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        getDebtsUseCase(),
        repository.getAllExpenses(),
        getPeopleUseCase()
    ) { debts, expenses, people ->
        val peopleMap = people.associateBy { it.id }
        
        val debtDisplays = debts.map { debt ->
            DebtDisplay(
                fromName = peopleMap[debt.fromPersonId]?.name ?: "Unknown",
                toName = peopleMap[debt.toPersonId]?.name ?: "Unknown",
                amount = "%.2f".format(debt.amount)
            )
        }

        val expenseDisplays = expenses.map { expense ->
            ExpenseDisplay(
                id = expense.id,
                description = expense.description,
                amount = "%.2f".format(expense.amount),
                payerName = peopleMap[expense.payerId]?.name ?: "Unknown",
                date = expense.date.toString() // formatted date ideally
            )
        }

        DashboardUiState(
            debts = debtDisplays,
            expenses = expenseDisplays
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardUiState()
    )


    fun settleExpense(expenseId: Long) {
        viewModelScope.launch {
            val expense = repository.getExpenseById(expenseId)
            expense?.let {
                repository.updateExpense(it.copy(isSettled = true))
            }
        }
    }
}
