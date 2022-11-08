package com.example.secretsanta.domain

data class PersonItem(
    val name: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
)
{
    companion object {
        //присваиваем значение, если настоящие значение id еще не установлено
        const val UNDEFINED_ID = -1
    }
}
