package com.example.secretsanta.domain

class GetPersonListUseCase(private val personListRepository: PersonListRepository) {

    fun getPersonList(): List<PersonItem>{
        return personListRepository.getPersonList()
    }
}