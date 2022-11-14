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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.secretsanta.R
import com.example.secretsanta.databinding.ActivityMainBinding
import com.example.secretsanta.domain.PersonItem

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var personListAdapter: PersonListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.personList.observe(this) {
            personListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvPersonList = findViewById<RecyclerView>(R.id.rv_person_list)
        with(rvPersonList) {
            personListAdapter = PersonListAdapter()
            adapter = personListAdapter
        }

        personListAdapter.onPersonItemLongClickListener = {
            viewModel.changeEnableState(it)
        }

        personListAdapter.onPersonItemClickListener = {
            TODO()
        }

        fun onPersonItemSwipeListener(rvPersonList: RecyclerView) {
            val callback = object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = personListAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.deletePersonItem(item)
                }
            }

            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(rvPersonList)

        }
    }

}


