package com.example.secretsanta.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secretsanta.data.PersonListRepositoryImpl  //исправить
import com.example.secretsanta.domain.DeletePersonItemUseCase
import com.example.secretsanta.domain.EditPersonItemUseCase
import com.example.secretsanta.domain.GetPersonListUseCase
import com.example.secretsanta.domain.PersonItem


class MainViewModel : ViewModel(){

    private val repository = PersonListRepositoryImpl

    private val getPersonListUseCase = GetPersonListUseCase(repository)
    private val deletePersonItemUseCase = DeletePersonItemUseCase(repository)
    private val editPersonItemUseCase = EditPersonItemUseCase(repository)

    val personList = getPersonListUseCase.getPersonList()

    fun deletePersonItem(personItem: PersonItem){
        deletePersonItemUseCase.deletePersonItem(personItem)
    }

    fun changeEnableState(personItem: PersonItem){
        val newItem = personItem.copy(enabled = !personItem.enabled)
        editPersonItemUseCase.editPersonItem(newItem)
    }
}