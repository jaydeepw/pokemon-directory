package com.github.jaydeepw.pokemondirectory.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

class DetailsFragment : Fragment(), DetailsContractInterface.View  {

    companion object {
        const val POKEMON_ID = "id_of_the_pokemon"
        val TAG = DetailsFragment::class.simpleName
    }

    private var progressIndicator: ProgressBar? = null
    private var pokemonId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pokemonId = arguments?.getInt(POKEMON_ID)
        return inflater.inflate(R.layout.fragment_details, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.progress_circular)
        Log.d(TAG, "ready to get poke for id: $pokemonId")
    }

    override fun showError(messageResId: Int) {

    }

    override fun showDetails(pokemon: Pokemon?) {
        var nameTextView = view?.findViewById<TextView>(R.id.textview_pokemon_name)
        nameTextView?.text = pokemon?.name
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}