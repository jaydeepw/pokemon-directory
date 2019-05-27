package com.github.jaydeepw.pokemondirectory

import org.junit.Assert
import org.junit.Test

class UtilsTest {
    @Test
    fun checkNetworkStatusCode500() {
        val stringRes = Utils.Companion.parseNetworkCode(500)
        Assert.assertEquals(R.string.error_server_500internal_error, stringRes)
    }

    @Test
    fun checkNetworkStatusCode401() {
        val stringRes = Utils.Companion.parseNetworkCode(401)
        Assert.assertEquals(R.string.error_server_401unaunthorized, stringRes)
    }

    @Test
    fun checkNetworkStatusCode404() {
        val stringRes = Utils.Companion.parseNetworkCode(404)
        Assert.assertEquals(R.string.error_server_404not_found, stringRes)
    }

    @Test
    fun checkNetworkStatusCode502() {
        val stringRes = Utils.Companion.parseNetworkCode(502)
        Assert.assertEquals(R.string.error_server_502bad_gateway, stringRes)
    }

    @Test
    fun checkNetworkStatusCode503() {
        val stringRes = Utils.Companion.parseNetworkCode(503)
        Assert.assertEquals(R.string.error_server_503unavailable, stringRes)
    }

    @Test
    fun checkNetworkStatusCode504() {
        val stringRes = Utils.Companion.parseNetworkCode(504)
        Assert.assertEquals(R.string.error_server_504gateway_timeout, stringRes)
    }

}