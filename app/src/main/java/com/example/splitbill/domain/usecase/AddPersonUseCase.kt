package com.example.splitbill.domain.usecase

import com.example.splitbill.domain.repository.SplitBillRepository
import javax.inject.Inject

class AddPersonUseCase @Inject constructor(
    private val repository: SplitBillRepository
) {
    suspend operator fun invoke(name: String) {
        val existing = repository.getPersonByName(name.trim())
        if (existing != null) {
            throw IllegalArgumentException("Person '$name' already exists.")
        }
        repository.addPerson(name.trim())
    }
}
