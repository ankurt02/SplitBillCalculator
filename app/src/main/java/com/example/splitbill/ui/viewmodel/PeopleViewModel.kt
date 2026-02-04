package com.example.splitbill.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitbill.domain.model.Person
import com.example.splitbill.domain.usecase.AddPersonUseCase
import com.example.splitbill.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val addPersonUseCase: AddPersonUseCase
) : ViewModel() {

    val people: StateFlow<List<Person>> = getPeopleUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _uiEvent = kotlinx.coroutines.flow.MutableSharedFlow<String>()
    val uiEvent = _uiEvent

    fun addPerson(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            try {
                addPersonUseCase(name)
                _uiEvent.emit("Added $name")
            } catch (e: Exception) {
                _uiEvent.emit(e.message ?: "Error adding person")
            }
        }
    }
}
