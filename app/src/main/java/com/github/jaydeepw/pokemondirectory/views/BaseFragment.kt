package com.github.jaydeepw.pokemondirectory.views

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment(), BaseView {

    protected var progressIndicator: ProgressBar? = null

    override fun showError(messageResId: Int) {
        if (view != null) {
            Snackbar.make(view!!, getString(messageResId), Snackbar.LENGTH_LONG)
        }
    }

    override fun showError(message: String) {
        if (view != null) {
            Snackbar.make(view!!, message, Snackbar.LENGTH_LONG)
        }
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