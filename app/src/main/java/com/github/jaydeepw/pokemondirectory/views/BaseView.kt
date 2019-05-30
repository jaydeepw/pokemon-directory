package com.github.jaydeepw.pokemondirectory.views

interface BaseView {
    fun showError(messageResId: Int)
    fun showError(message: String)
    fun showProgress()
    fun hideProgress()
}