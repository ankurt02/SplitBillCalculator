package com.example.splitbill.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.splitbill.domain.model.Person

@Entity(tableName = "people")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)

fun PersonEntity.toDomain() = Person(id = id, name = name)
fun Person.toEntity() = PersonEntity(id = id, name = name)
