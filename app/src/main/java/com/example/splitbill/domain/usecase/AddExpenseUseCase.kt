package com.example.splitbill.domain.usecase

import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.repository.SplitBillRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repository: SplitBillRepository
) {
    suspend operator fun invoke(expense: Expense) {
        repository.addExpense(expense)
    }
}
