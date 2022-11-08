package com.example.secretsanta.domain

import androidx.lifecycle.LiveData

interface PersonListRepository {

    fun addPersonItem(personItem: PersonItem)

    fun deletePersonItem(personItem: PersonItem)

    fun editPersonItem(personItem: PersonItem)

    fun getPersonItem(personItemId: Int): PersonItem

    fun getPersonList(): LiveData<List<PersonItem>>
}