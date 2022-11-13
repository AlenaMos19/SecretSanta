package com.example.secretsanta.presentation

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.secretsanta.R
import com.example.secretsanta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var personListAdapter: PersonListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.personList.observe(this) {
            personListAdapter.personList = it
        }
    }

    private fun setupRecyclerView() {
        val rvPersonList = findViewById<RecyclerView>(R.id.rv_person_list)
        with(rvPersonList) {
            personListAdapter = PersonListAdapter()
            adapter = personListAdapter
        }
    }




}