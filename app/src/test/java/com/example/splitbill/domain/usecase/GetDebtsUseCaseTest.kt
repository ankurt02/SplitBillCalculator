package com.example.splitbill.domain.usecase

import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.repository.SplitBillRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Date

// Mock repository manually since simple enough
class FakeRepository : SplitBillRepository {
    override fun getAllPeople() = flowOf(emptyList<Person>())
    override suspend fun addPerson(name: String) {}
    override suspend fun getPersonById(id: Long) = null
    override suspend fun getPersonByName(name: String) = null
    override fun getAllExpenses() = flowOf(emptyList<Expense>())
    override suspend fun addExpense(expense: Expense) {}
    override suspend fun updateExpense(expense: Expense) {}
    override suspend fun getExpenseById(id: Long) = null
}

class GetDebtsUseCaseTest {

    @Test
    fun `test debt simplification - simple A pays for B`() = runBlocking {
        val people = listOf(
            Person(1, "A"),
            Person(2, "B")
        )
        val expenses = listOf(
            Expense(
                description = "Lunch",
                amount = 100.0,
                payerId = 1, // A
                shares = mapOf(1L to 50.0, 2L to 50.0) // A and B split 100 equally
            )
        )
        // Split: A pays 100. Share is 50.
        // A: Balance +100 (paid) - 50 (consumed) = +50
        // B: Balance 0 - 50 (consumed) = -50
        // Result: B owes A 50.

        val useCase = GetDebtsUseCase(FakeRepository())
        // We use reflection or modify visibility to access private calculateDebts, 
        // OR we just use the public invoke but we need to mock repository flows properly.
        // For this test, I'll access the private method via reflection or just trust the logic if public.
        // Wait, I made it private but commented "Public for testing". I should change it or use invoke.

        // Let's use invoke with mocked flows for better integration test style.
        val repo = object : SplitBillRepository by FakeRepository() {
            override fun getAllPeople() = flowOf(people)
            override fun getAllExpenses() = flowOf(expenses)
        }

        val debts = GetDebtsUseCase(repo).invoke().first()

        assertEquals(1, debts.size)
        val debt = debts[0]
        assertEquals(2L, debt.fromPersonId) // B
        assertEquals(1L, debt.toPersonId) // A
        assertEquals(50.0, debt.amount, 0.01)
    }

    @Test
    fun `test debt netting - A owes B 100, B owes A 80`() = runBlocking {
        val people = listOf( Person(1, "A"), Person(2, "B") )

        // A owes B 100 => B paid 100 for A (only A involved)
        val exp1 = Expense(description = "X", amount = 100.0, payerId = 2, shares = mapOf(1L to 100.0))

        // B owes A 80 => A paid 80 for B (only B involved)
        val exp2 = Expense(description = "Y", amount = 80.0, payerId = 1, shares = mapOf(2L to 80.0))

        // Calc:
        // A: 
        //   Exp1: -100 (consumed)
        //   Exp2: +80 (paid)
        //   Net: -20
        // B:
        //   Exp1: +100 (paid)
        //   Exp2: -80 (consumed)
        //   Net: +20

        // Result: A owes B 20.

        val repo = object : SplitBillRepository by FakeRepository() {
            override fun getAllPeople() = flowOf(people)
            override fun getAllExpenses() = flowOf(listOf(exp1, exp2))
        }

        val debts = GetDebtsUseCase(repo).invoke().first()

        assertEquals(1, debts.size)
        val debt = debts[0]
        assertEquals(1L, debt.fromPersonId) // A
        assertEquals(2L, debt.toPersonId) // B
        assertEquals(20.0, debt.amount, 0.01)
    }
}
