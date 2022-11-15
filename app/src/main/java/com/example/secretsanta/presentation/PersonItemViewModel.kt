package com.example.secretsanta.presentation

import androidx.lifecycle.ViewModel
import com.example.secretsanta.data.PersonListRepositoryImpl
import com.example.secretsanta.domain.AddPersonItemUseCase
import com.example.secretsanta.domain.EditPersonItemUseCase
import com.example.secretsanta.domain.GetPersonItemUseCase
import com.example.secretsanta.domain.PersonItem


class PersonItemViewModel(private val getPersonItemUseCase: GetPersonItemUseCase,
                          private val addPersonItemUseCase: AddPersonItemUseCase,
                          private val editPersonItemUseCase: EditPersonItemUseCase
) : ViewModel(){

   // private val repository = PersonListRepositoryImpl

    fun getPersonItem(personItemId: Int){
        val item = getPersonItemUseCase.getPersonItem(personItemId)
    }

    fun addPersonItemUseCase(inputName : String?){
        val name = parseName(inputName)
        val correctName = correctName(name)
        if(correctName) {
            val personItem = PersonItem(name, true)
            addPersonItemUseCase.addPersonItem(personItem)
        }
    }

    fun editPersonItemUseCase(inputName : String?){
        val name = parseName(inputName)
        val correctName = correctName(name)
        if(correctName) {
            val personItem = PersonItem(name, true)
            editPersonItemUseCase.editPersonItem(personItem)
        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun correctName(name: String): Boolean{
        var result = true
        if (name.isBlank()){
            //TODO: отображение ошибки
            result = false
        }
        return result
    }
}