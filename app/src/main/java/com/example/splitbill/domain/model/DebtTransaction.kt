package com.example.splitbill.domain.model

data class DebtTransaction(
    val fromPersonId: Long,
    val toPersonId: Long,
    val amount: Double
)
