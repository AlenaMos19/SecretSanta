package com.example.secretsanta.data

import com.example.secretsanta.domain.PersonItem
import com.example.secretsanta.domain.PersonListRepository

object PersonListRepositoryImpl : PersonListRepository {

    private val personList = mutableListOf<PersonItem>()

    private var autoIncremetId = 0

    override fun addPersonItem(personItem: PersonItem) {
        if (personItem.id == PersonItem.UNDEFINED_ID) {
            personItem.id = autoIncremetId++
        }
        personList.add(personItem)
    }

    override fun deletePersonItemUseCase(personItem: PersonItem) {
        personList.remove(personItem)
    }

    override fun editPersonItem(personItem: PersonItem) {
        val exPerson = getPersonItem(personItem.id)
        personList.remove(exPerson)
        addPersonItem(personItem)
    }

    override fun getPersonItem(personItemId: Int): PersonItem {
        return personList.find {
            it.id == personItemId
        } ?: throw RuntimeException("Person ith this id $personItemId not found")
    }

    override fun getPersonList(): List<PersonItem> {
        return personList.toList()
    }

}