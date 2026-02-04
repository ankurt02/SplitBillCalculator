package com.example.splitbill.domain.usecase

import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.repository.SplitBillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val repository: SplitBillRepository
) {
    operator fun invoke(): Flow<List<Person>> {
        return repository.getAllPeople()
    }
}
