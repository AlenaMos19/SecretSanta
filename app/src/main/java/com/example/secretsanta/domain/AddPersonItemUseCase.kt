package com.example.secretsanta.domain

class AddPersonItemUseCase(private val personListRepository: PersonListRepository) {

    fun addPersonItem(personItem: PersonItem){
        personListRepository.addPersonItem(personItem)
    }
}