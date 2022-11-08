package com.example.secretsanta.domain

interface PersonListRepository {

    fun addPersonItem(personItem: PersonItem)

    fun deletePersonItemUseCase(personItem: PersonItem)

    fun editPersonItem(personItem: PersonItem)

    fun getPersonItem(personItemId: Int): PersonItem

    fun getPersonList(): List<PersonItem>
}