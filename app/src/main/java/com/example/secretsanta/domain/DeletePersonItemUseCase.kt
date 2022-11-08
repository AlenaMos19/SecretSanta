package com.example.secretsanta.domain

class DeletePersonItemUseCase(private val personListRepository: PersonListRepository){

    fun deletePersonItemUseCase(personItem: PersonItem){
        personListRepository.deletePersonItemUseCase(personItem)
    }
}