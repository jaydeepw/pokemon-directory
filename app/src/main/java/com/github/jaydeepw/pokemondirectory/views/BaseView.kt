package com.github.jaydeepw.pokemondirectory.views

open interface BaseView {
    fun showError(messageResId: Int)
    fun showProgress()
    fun hideProgress()
}