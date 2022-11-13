package com.example.secretsanta.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.secretsanta.R
import com.example.secretsanta.domain.PersonItem
import com.example.secretsanta.presentation.PersonListAdapter.PersonItemViewHolder

class PersonListAdapter: RecyclerView.Adapter<PersonItemViewHolder>() {

    class PersonItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
    }

    var personList = listOf<PersonItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_mix_person_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_mix_person_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PersonItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PersonItemViewHolder, position: Int) {
        val personItem = personList[position]
        viewHolder.view.setOnLongClickListener {
            true
        }
        viewHolder.tvName.text = personItem.name

    }

    override fun onViewRecycled(viewHolder: PersonItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(
            viewHolder.view.context,
            android.R.color.white
        ))
    }

    override fun getItemViewType(position: Int): Int {
        val item = personList[position]
        return if(item.enabled){
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
    }
}