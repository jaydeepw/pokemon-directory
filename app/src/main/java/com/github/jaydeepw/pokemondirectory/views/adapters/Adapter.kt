package com.github.jaydeepw.pokemondirectory.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(val items : ArrayList<Pokemon>?, val context: Context
              ,var presenter: MainContractInterface.Presenter) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHoldder =  ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false),
            presenter)
        return viewHoldder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = items?.get(position)
        holder.itemView.tag = pokemon
        holder.pokemonTitle?.text = pokemon?.name?.capitalize()
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

class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

    private var presenter: MainContractInterface.Presenter
    var pokemonTitle : TextView

    constructor(view: View, presenter: MainContractInterface.Presenter) : super(view) {
        this.presenter = presenter
        itemView.setOnClickListener(this)
        // Holds the TextView that will add each animal to
        pokemonTitle = view.textview_pokemon
    }

    override fun onClick(v: View?) {
        presenter.onItemClick(itemView.tag as Pokemon)
    }
}