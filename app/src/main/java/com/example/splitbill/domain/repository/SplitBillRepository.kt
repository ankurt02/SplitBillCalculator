package com.example.splitbill.domain.repository

import com.example.splitbill.domain.model.Expense
import com.example.splitbill.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface SplitBillRepository {
    fun getAllPeople(): Flow<List<Person>>
    suspend fun addPerson(name: String)
    suspend fun getPersonById(id: Long): Person?
    suspend fun getPersonByName(name: String): Person?
    
    fun getAllExpenses(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun getExpenseById(id: Long): Expense?
}
