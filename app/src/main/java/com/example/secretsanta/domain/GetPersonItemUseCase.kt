package com.example.secretsanta.domain

class GetPersonItemUseCase(private val personListRepository: PersonListRepository) {

    fun getPersonItem(personItemId: Int): PersonItem{
        return personListRepository.getPersonItem(personItemId)
    }
}