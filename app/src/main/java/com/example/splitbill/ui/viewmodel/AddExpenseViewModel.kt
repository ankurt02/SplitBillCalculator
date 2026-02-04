package com.example.splitbill.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.usecase.AddExpenseUseCase
import com.example.splitbill.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: com.example.splitbill.domain.repository.SplitBillRepository,
    getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {

    val people: StateFlow<List<Person>> = getPeopleUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _expenseState = kotlinx.coroutines.flow.MutableStateFlow<Expense?>(null)
    val expenseState: StateFlow<Expense?> = _expenseState

    fun loadExpense(id: Long) {
        if (id == -1L) return
        viewModelScope.launch {
            _expenseState.value = repository.getExpenseById(id)
        }
    }

    fun saveExpense(id: Long, description: String, amount: Double, payerId: Long, shares: Map<Long, Double>) {
        if (description.isBlank() || amount <= 0 || payerId == 0L) return
        
        viewModelScope.launch {
            val expense = Expense(
                id = if (id == -1L) 0 else id,
                description = description,
                amount = amount,
                payerId = payerId,
                shares = shares,
                isSettled = false
            )
            
            if (id == -1L) {
                repository.addExpense(expense)
            } else {
                repository.updateExpense(expense)
            }
        }
    }
}
