package com.example.secretsanta.domain

class DeletePersonItemUseCase(private val personListRepository: PersonListRepository){

    fun deletePersonItem(personItem: PersonItem){
        personListRepository.deletePersonItem(personItem)
    }
}