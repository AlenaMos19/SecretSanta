package com.example.secretsanta.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _personItem = MutableLiveData<PersonItem>()
    val personItem: LiveData<PersonItem>
        get() = _personItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getPersonItem(personItemId: Int){
        val item = getPersonItemUseCase.getPersonItem(personItemId)
        _personItem.value = item
    }

    fun addPersonItemUseCase(inputName : String?){
        val name = parseName(inputName)
        val correctName = correctName(name)
        if(correctName) {
            val personItem = PersonItem(name, true)
            addPersonItemUseCase.addPersonItem(personItem)
            _shouldCloseScreen.value = Unit
        }
    }

    fun editPersonItemUseCase(inputName : String?){
        val name = parseName(inputName)
        val correctName = correctName(name)
        if(correctName) {
            _personItem.value?.let {
                val item = it.copy(name = name)
                editPersonItemUseCase.editPersonItem(item)
                _shouldCloseScreen.value = Unit }
        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun correctName(name: String): Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }
}