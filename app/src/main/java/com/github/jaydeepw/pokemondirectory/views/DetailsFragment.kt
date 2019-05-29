package com.github.jaydeepw.pokemondirectory.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.jaydeepw.pokemondirectory.Constants
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.di.DaggerFragmentComponent
import com.github.jaydeepw.pokemondirectory.di.ModelsModule
import com.github.jaydeepw.pokemondirectory.di.NetworkModule
import com.github.jaydeepw.pokemondirectory.di.PresenterModule
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.presenters.DetailsPresenter
import javax.inject.Inject

class DetailsFragment : BaseFragment(), DetailsContractInterface.View {

    companion object {
        const val POKEMON_ID = "id_of_the_pokemon"
        val TAG = DetailsFragment::class.simpleName
    }

    private var pokemonId: Int? = null

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = PokemonRepository(activity?.application!!)
        val daggerFragmentComp = DaggerFragmentComponent.builder()
            .presenterModule(PresenterModule(this, repository))
            .modelsModule(ModelsModule())
            .networkModule(NetworkModule(Constants.Companion.BASE_URL))
            .build()
        daggerFragmentComp.inject(this)
        daggerFragmentComp.inject(repository)
        daggerFragmentComp.inject(repository.detailsModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pokemonId = arguments?.getInt(POKEMON_ID)
        return inflater.inflate(R.layout.fragment_details, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.progress_circular)
        Log.d(TAG, "ready to get poke for id: $pokemonId")
        if (pokemonId != null) {
            presenter.onGetDetails(pokemonId!!)
        } else {
            Log.wtf(TAG, "what! Pokemon id should not be null here")
        }
    }

    override fun showDetails(pokemon: Pokemon?) {
        val view = view?.findViewById<View>(R.id.data_holder1)
        view?.visibility = View.VISIBLE
        showName(pokemon)
        showAvatar(pokemon)
        showPhysicalDetails(pokemon)
        showMoveCount(pokemon)
    }

    private fun showMoveCount(pokemon: Pokemon?) {
        val moveCountTextView = view?.findViewById<TextView>(R.id.textview_pokemon_movecount)
        moveCountTextView?.text = Integer.toString(pokemon?.moves?.size!!)
    }

    private fun showName(pokemon: Pokemon?) {
        val nameTextView = view?.findViewById<TextView>(R.id.textview_pokemon_name)
        nameTextView?.text = pokemon?.name?.capitalize()
    }

    private fun showPhysicalDetails(pokemon: Pokemon?) {
        val weightTextView = view?.findViewById<TextView>(R.id.textview_pokemon_weight)
        weightTextView?.text = Integer.toString(pokemon?.weight!!)

        val heightTextView = view?.findViewById<TextView>(R.id.textview_pokemon_height)
        heightTextView?.text = Integer.toString(pokemon?.height!!)

        val experienceTextView = view?.findViewById<TextView>(R.id.textview_pokemon_experience)
        experienceTextView?.text = Integer.toString(pokemon?.base_experience!!)
    }

    private fun showAvatar(pokemon: Pokemon?) {
        val imageView = view?.findViewById<ImageView>(R.id.textview_pokemon_image)
        Glide.with(this).load(pokemon?.sprites?.front_default).into(imageView!!)
    }

    override fun showProgress() {
        progressIndicator?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        if (progressIndicator?.isShown!!) {
            progressIndicator?.visibility = View.GONE
        }
    }
}