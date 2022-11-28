package com.example.secretsanta.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.secretsanta.R
import com.example.secretsanta.domain.PersonItem
import com.google.android.material.textfield.TextInputLayout

class PersonActivity: AppCompatActivity() {

    private lateinit var viewModel: PersonItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var buttonSave: Button

    private var screenMode = MODE_UNKNOWN
    private var personItemId = PersonItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        parseIntent()
        viewModel = ViewModelProvider(this)[PersonItemViewModel::class.java]
        initViews()
        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.errorInputName.observe(this){
            val message = if (it) {
                getString(com.google.android.material.R.string.error_icon_content_description)
            } else {
                null
            }
            tilName.error = message
        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }

    private fun launchRightMode(){
        when(screenMode){
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListener(){
        etName.addTextChangedListener(/* watcher = */ object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun launchEditMode() {
        viewModel.getPersonItem(personItemId)
        viewModel.personItem.observe(this){
            etName.setText(it.name)
        }
        buttonSave.setOnClickListener {
            viewModel.editPersonItemUseCase(etName.text?.toString())
        }
    }
    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addPersonItemUseCase(etName.text?.toString())
        }
    }

    private  fun parseIntent(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if(mode != MODE_ADD && mode != MODE_EDIT){
            throw RuntimeException("Unknown screen mode : $mode")
        }
        screenMode = mode
        if(screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_PERSON_ID)) {
                throw RuntimeException("Param person id is absent")
            }
            personItemId = intent.getIntExtra(EXTRA_PERSON_ID, PersonItem.UNDEFINED_ID)
        }

    }

    private fun initViews(){
        tilName= findViewById(R.id.til_name)
        etName= findViewById(R.id.et_name)
        buttonSave = findViewById(R.id.save_button)
    }

    companion object{

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_PERSON_ID = "extra_person_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

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