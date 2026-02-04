package com.example.splitbill.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.splitbill.data.local.dao.ExpenseDao
import com.example.splitbill.data.local.dao.PersonDao
import com.example.splitbill.data.local.entity.ExpenseEntity
import com.example.splitbill.data.local.entity.PersonEntity

@Database(entities = [PersonEntity::class, ExpenseEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun expenseDao(): ExpenseDao
}
