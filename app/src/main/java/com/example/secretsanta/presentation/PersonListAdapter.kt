package com.example.secretsanta.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.secretsanta.R
import com.example.secretsanta.domain.PersonItem


class PersonListAdapter : ListAdapter<PersonItem, PersonItemViewHolder>(PersonItemDiffCallBack()) {


    var onPersonItemLongClickListener: ((PersonItem) -> Unit)? = null
    var onPersonItemClickListener: ((PersonItem) -> Unit)? = null

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
        val personItem = getItem(position)
        viewHolder.view.setOnLongClickListener {
            onPersonItemLongClickListener?.invoke(personItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onPersonItemClickListener?.invoke(personItem)
        }
        viewHolder.tvName.text = personItem.name
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item.enabled){
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }


    companion object {

        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
    }
}