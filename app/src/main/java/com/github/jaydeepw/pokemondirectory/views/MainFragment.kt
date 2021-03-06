package com.github.jaydeepw.pokemondirectory.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.pokemondirectory.Constants
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.di.DaggerFragmentComponent
import com.github.jaydeepw.pokemondirectory.di.ModelsModule
import com.github.jaydeepw.pokemondirectory.di.NetworkModule
import com.github.jaydeepw.pokemondirectory.di.PresenterModule
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.presenters.MainPresenter
import com.github.jaydeepw.pokemondirectory.views.adapters.Adapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class MainFragment : BaseFragment(), MainContractInterface.View {

    private var recycleListView: RecyclerView? = null

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PokemonRepository(activity?.application!!)
        val daggerFragmentComp = DaggerFragmentComponent.builder()
                .presenterModule(PresenterModule(this, repository))
                .modelsModule(ModelsModule())
                .networkModule(NetworkModule(Constants.Companion.BASE_URL))
                .build()

        daggerFragmentComp.inject(this)
        daggerFragmentComp.inject(presenter)
        daggerFragmentComp.inject(repository)
        daggerFragmentComp.inject(repository.mainModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.progress_circular)
        recycleListView = view.findViewById(R.id.list_items)
        presenter.onGetData()
    }

    override fun showData(list: ArrayList<Pokemon>?) {
        // Because this is a list of albums, makes more sense in using a GridLayoutManger instead
        // of the Linear layout manager.
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        recycleListView?.layoutManager = LinearLayoutManager(context)

        // Access the RecyclerView Adapter and load the data into it
        val adapter = Adapter(list, context!!, presenter)
        recycleListView?.adapter = adapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(list: List<Pokemon>) {
        // Toast.makeText(activity, "==> albums.size " + albums.size, Toast.LENGTH_SHORT).show()
        var adapter = recycleListView?.adapter as Adapter
        if (adapter == null) {
            adapter = Adapter(list as ArrayList<Pokemon>, context!!, presenter)
            recycleListView?.adapter = adapter
        } else {
            adapter.updateAll(list)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun showDetails(pokemon: Pokemon?) {
        val fragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putInt(DetailsFragment.POKEMON_ID, pokemon?.getPokemonId()!!)
        fragment.arguments = bundle
        val ft = fragmentManager?.beginTransaction()
        ft?.replace(R.id.dynamic_fragment_frame_layout, fragment)
        ft?.addToBackStack(null)
        ft?.commit()
    }
}