package com.google.advancedsoft.network

import android.util.Log
import com.google.gson.JsonParseException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

/**
 * Created by Simon Gerges on 6/26/18.
 * <p>
 *
 */
open class AppError(
    var errorCode: Int? = null,
    var errorMessage: String? = null,
    var serverRequestID: String? = null,
    errorCause: Throwable? = null
) : Exception(errorCause) {

    companion object {

        private const val PARSING_ERROR_CODE = -100
        private const val TIMEOUT_ERROR_CODE = -102
        private const val UNKNOWN_ERROR_CODE = -104
        const val JOB_CANCELLATION_CODE = -105
        const val NETWORK_ERROR_CODE = -101
        const val FORCE_UPDATE_ERROR_CODE = -103
        const val NO_ROOMS_AVAILABILITY = 1030
        const val SUCCESS_CODE = 200

        fun createGeneralError(cause: Throwable? = null): AppError {
            val parsingError = AppError(errorCause = cause)
            parsingError.errorCode = when (cause) {
                is UnknownHostException -> NETWORK_ERROR_CODE
                is SocketTimeoutException -> TIMEOUT_ERROR_CODE
                is JsonParseException -> PARSING_ERROR_CODE
                is CancellationException -> JOB_CANCELLATION_CODE
                else -> UNKNOWN_ERROR_CODE
            }
            parsingError.errorMessage = cause?.message
            Log.e("AppError", parsingError.errorMessage, parsingError)
            return parsingError
        }


    }

    fun isNetworkError() = errorCode == NETWORK_ERROR_CODE

    override fun toString(): String {
        return "ErrorCode: $errorCode, ErrorMessage: $errorMessage , serverRequestID: ${serverRequestID.orEmpty()}"
    }
}
