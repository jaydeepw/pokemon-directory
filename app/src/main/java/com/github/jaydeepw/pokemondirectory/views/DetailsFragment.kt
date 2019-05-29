package com.github.jaydeepw.pokemondirectory.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

class DetailsFragment : Fragment(), DetailsContractInterface.View  {

    private var progressIndicator: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.progress_circular)
        // presenter.onGetData()
    }

    override fun showError(messageResId: Int) {

    }

    override fun showDetails(pokemon: Pokemon?) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}