package com.example.secretsanta.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.secretsanta.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        val buttonAddPerson = findViewById<FloatingActionButton>(R.id.button_add_person)
        buttonAddPerson.setOnClickListener {
            val intent = PersonActivity.newIntentAdd(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rvPersonList = findViewById<RecyclerView>(R.id.rv_person_list)
        with(rvPersonList) {
            personListAdapter = PersonListAdapter()
            adapter = personListAdapter
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
                    val item = personListAdapter.personList[viewHolder.adapterPosition]
                    viewModel.deletePersonItem(item)
                }
            }

            personListAdapter.onPersonItemLongClickListener = {
                viewModel.changeEnableState(it)
            }

            personListAdapter.onPersonItemClickListener = {
                val intent = PersonActivity.newIntentEdit(this, it.id)
                startActivity(intent)
            }

            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(rvPersonList)

        }
    }

}


