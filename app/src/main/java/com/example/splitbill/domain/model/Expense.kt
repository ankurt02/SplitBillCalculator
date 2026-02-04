package com.example.splitbill.domain.model

import java.util.Date

data class Expense(
    val id: Long = 0,
    val description: String,
    val amount: Double,
    val payerId: Long,
    val date: Date = Date(), // Simplified date for now
    // In a more complex app, we'd list who this is split among. 
    // For now assuming equal split among ALL people or a subset.
    // Let's add a list of involved person IDs to be precise.
    // If empty, implies ALL.
    // Map of Person ID to the amount they owe in this expense
    // If map is empty, implies equal split among ALL people (managed by business logic if needed, but explicit shares are safer)
    val shares: Map<Long, Double> = emptyMap(),
    val isSettled: Boolean = false
)
