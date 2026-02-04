package com.example.splitbill.domain.usecase

import com.example.splitbill.domain.model.DebtTransaction
import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.repository.SplitBillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.min

class GetDebtsUseCase @Inject constructor(
    private val repository: SplitBillRepository
) {
    operator fun invoke(): Flow<List<DebtTransaction>> {
        return combine(
            repository.getAllPeople(),
            repository.getAllExpenses()
        ) { people, expenses ->
            calculateDebts(people, expenses)
        }
    }

    // Public for testing purposes if needed, or keeping logic encapsulated here
    private fun calculateDebts(people: List<Person>, expenses: List<Expense>): List<DebtTransaction> {
        val balances = mutableMapOf<Long, Double>()
        people.forEach { balances[it.id] = 0.0 }

        // Calculate net balances
        // Calculate net balances
        expenses.filter { !it.isSettled }.forEach { expense ->
            val payerId = expense.payerId
            val amount = expense.amount
            
            // Credit the payer for the full amount they paid
            balances[payerId] = (balances[payerId] ?: 0.0) + amount

            // Debit the consumers
            if (expense.shares.isNotEmpty()) {
                // Explicit shares (Equal or Unequal)
                expense.shares.forEach { (personId, shareAmount) ->
                     balances[personId] = (balances[personId] ?: 0.0) - shareAmount
                }
            } else {
                // Implicit: Equal split among ALL people
                 if (people.isNotEmpty()) {
                    val splitAmount = amount / people.size
                    people.forEach { person ->
                        balances[person.id] = (balances[person.id] ?: 0.0) - splitAmount
                    }
                }
            }
        }

        // Separate into debtors (negative) and creditors (positive)
        val debtors = mutableListOf<Pair<Long, Double>>()
        val creditors = mutableListOf<Pair<Long, Double>>()

        balances.forEach { (id, balance) ->
            // Using a small epsilon for float comparison safety
            if (balance < -0.01) debtors.add(id to balance)
            if (balance > 0.01) creditors.add(id to balance)
        }

        // Sort by magnitude to minimize transactions (heuristic)
        // Debtors: most negative first (ascending)
        debtors.sortBy { it.second } 
        // Creditors: most positive first (descending)
        creditors.sortByDescending { it.second } 

        val transactions = mutableListOf<DebtTransaction>()
        var i = 0 // debtor index
        var j = 0 // creditor index

        // Using MutableLists to track remaining amounts
        val currentDebtors = debtors.map { it.first to it.second }.toMutableList()
        val currentCreditors = creditors.map { it.first to it.second }.toMutableList()

        while (i < currentDebtors.size && j < currentCreditors.size) {
            val debtor = currentDebtors[i]
            val creditor = currentCreditors[j]

            val debtAmount = abs(debtor.second)
            val creditAmount = creditor.second
            
            val amount = min(debtAmount, creditAmount)
            
            // Record transaction
            transactions.add(DebtTransaction(
                fromPersonId = debtor.first,
                toPersonId = creditor.first,
                amount = amount
            ))

            // Update remaining balances
            val remainingDebt = debtAmount - amount
            val remainingCredit = creditAmount - amount

            // Update state
            if (remainingDebt < 0.01) {
                i++ // Debtor settled
            } else {
                currentDebtors[i] = debtor.first to -remainingDebt
            }

            if (remainingCredit < 0.01) {
                j++ // Creditor settled
            } else {
                currentCreditors[j] = creditor.first to remainingCredit
            }
        }

        return transactions
    }
}
