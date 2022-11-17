package com.example.secretsanta.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secretsanta.R

class PersonActivity: AppCompatActivity() {

    //private lateinit var viewModel : PersonItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
    }

    companion object{

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_PERSON_ID = "extra_person_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAdd(context: Context): Intent{
            val intent = Intent(context, PersonActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, personItemId: Int): Intent{
            val intent = Intent(context, PersonActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_PERSON_ID, personItemId)
            return intent
        }

    }
}