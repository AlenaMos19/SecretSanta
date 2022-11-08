package com.example.secretsanta.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.secretsanta.domain.PersonItem
import com.example.secretsanta.domain.PersonListRepository

object PersonListRepositoryImpl : PersonListRepository {

    private val personListLD = MutableLiveData<List<PersonItem>>()
    private val personList = mutableListOf<PersonItem>()

    private var autoIncrementId = 0

    override fun addPersonItem(personItem: PersonItem) {
        if (personItem.id == PersonItem.UNDEFINED_ID) {
            personItem.id = autoIncrementId++
        }
        personList.add(personItem)
        updateList()
    }

    override fun deletePersonItem(personItem: PersonItem) {
        personList.remove(personItem)
        updateList()
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

    override fun getPersonList(): LiveData<List<PersonItem>> {
        return personListLD
    }

    private fun updateList() {
        personListLD.value = personList.toList()
    }
}