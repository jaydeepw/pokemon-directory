package com.github.jaydeepw.pokemondirectory.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(val items : ArrayList<Pokemon>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = items?.get(position)
        holder.pokemonTitle?.text = pokemon?.title
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items?.size!!
    }

    fun updateAll(newItems: List<Pokemon>) {
        items?.clear()
        items?.addAll(newItems)
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val pokemonTitle = view.textview_pokemon
}