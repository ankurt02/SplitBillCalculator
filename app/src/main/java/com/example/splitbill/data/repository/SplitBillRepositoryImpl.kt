package com.example.splitbill.data.repository

import com.example.splitbill.data.local.dao.ExpenseDao
import com.example.splitbill.data.local.dao.PersonDao
import com.example.splitbill.data.local.entity.toDomain
import com.example.splitbill.data.local.entity.toEntity
import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.repository.SplitBillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SplitBillRepositoryImpl @Inject constructor(
    private val personDao: PersonDao,
    private val expenseDao: ExpenseDao
) : SplitBillRepository {

    override fun getAllPeople(): Flow<List<Person>> {
        return personDao.getAllPeople().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addPerson(name: String) {
        // ID 0 triggers auto-generate
        personDao.insertPerson(Person(name = name).toEntity())
    }

    override suspend fun getPersonById(id: Long): Person? {
        return personDao.getPersonById(id)?.toDomain()
    }

    override suspend fun getPersonByName(name: String): Person? {
        return personDao.getPersonByName(name)?.toDomain()
    }

    override fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addExpense(expense: Expense) {
        expenseDao.insertExpense(expense.toEntity())
    }

    override suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense.toEntity())
    }

    override suspend fun getExpenseById(id: Long): Expense? {
        return expenseDao.getExpenseById(id)?.toDomain()
    }
}
