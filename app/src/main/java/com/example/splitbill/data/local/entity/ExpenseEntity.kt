package com.example.splitbill.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.splitbill.domain.model.Expense
import java.util.Date

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
    val amount: Double,
    val payerId: Long,
    val date: Long, // Store as timestamp
    val shares: String, // Comma separated "id:amount" pairs e.g. "1:10.5,2:20.0"
    val isSettled: Boolean = false
)

fun ExpenseEntity.toDomain(): Expense {
    val sharesMap = if (shares.isBlank()) {
        emptyMap()
    } else {
        try {
            shares.split(",").associate {
                val (idStr, amountStr) = it.split(":")
                idStr.toLong() to amountStr.toDouble()
            }
        } catch (e: Exception) {
            emptyMap()
        }
    }
    return Expense(
        id = id,
        description = description,
        amount = amount,
        payerId = payerId,
        date = Date(date),
        shares = sharesMap,
        isSettled = isSettled
    )
}

fun Expense.toEntity(): ExpenseEntity {
    val sharesString = shares.entries.joinToString(",") { "${it.key}:${it.value}" }
    return ExpenseEntity(
        id = id,
        description = description,
        amount = amount,
        payerId = payerId,
        date = date.time,
        shares = sharesString,
        isSettled = isSettled
    )
}
