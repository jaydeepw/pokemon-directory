package com.github.jaydeepw.pokemondirectory

import android.content.Context
import android.net.ConnectivityManager
import java.net.HttpURLConnection

class Utils {

    object Companion {

        /**
         * Checks if the Internet connection is available.
         *
         * @return Returns true if the Internet connection is available. False otherwise.
         */
        fun isInternetAvailable(ctx: Context): Boolean {
            val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo

            // if network is NOT available networkInfo will be null
            // otherwise check if we are connected
            return networkInfo != null && networkInfo.isConnected
        }

        fun parseNetworkCode(statusCode: Int): Int {
            val resId = when (statusCode) {
                HttpURLConnection.HTTP_BAD_REQUEST -> R.string.error_server_400bad_request
                HttpURLConnection.HTTP_NOT_FOUND -> R.string.error_server_404not_found
                HttpURLConnection.HTTP_UNAUTHORIZED -> R.string.error_server_401unaunthorized
                HttpURLConnection.HTTP_FORBIDDEN -> R.string.error_server_403forbidden
                HttpURLConnection.HTTP_BAD_METHOD -> R.string.error_server_405forbidden
                HttpURLConnection.HTTP_INTERNAL_ERROR -> R.string.error_server_500internal_error
                HttpURLConnection.HTTP_NOT_IMPLEMENTED -> R.string.error_server_501not_implemented
                HttpURLConnection.HTTP_BAD_GATEWAY -> R.string.error_server_502bad_gateway
                HttpURLConnection.HTTP_UNAVAILABLE -> R.string.error_server_503unavailable
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> R.string.error_server_504gateway_timeout
                else -> R.string.msg_error_general
            }
            return resId
        }

    }   // end Companion object
}