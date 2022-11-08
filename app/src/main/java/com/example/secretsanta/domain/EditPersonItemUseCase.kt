package com.example.secretsanta.domain

class EditPersonItemUseCase(private val personListRepository: PersonListRepository) {

    fun editPersonItem(personItem: PersonItem){
        personListRepository.editPersonItem(personItem)
    }
}