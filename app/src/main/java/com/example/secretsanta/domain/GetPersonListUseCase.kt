package com.example.secretsanta.domain

import androidx.lifecycle.LiveData

class GetPersonListUseCase(private val personListRepository: PersonListRepository) {

    fun getPersonList(): LiveData<List<PersonItem>>{
        return personListRepository.getPersonList()
    }
}