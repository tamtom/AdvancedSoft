package com.google.advancedsoft.network

sealed class AppResult<out T> {
    object Loading : AppResult<Nothing>()
    data class Success<T>(var data: T) : AppResult<T>()
    data class Failure(val error: AppError) : AppResult<Nothing>()
    object Completed : AppResult<Nothing>()

    companion object {
        fun loading(): AppResult<Nothing> = Loading

        fun <T> success(data: T): AppResult<T> = Success(data)

        fun error(error: AppError): AppResult<Nothing> = Failure(error)

        fun error(t: Throwable? = null): AppResult<Nothing> = error(AppError.createGeneralError(t))

        fun completed(): AppResult<Nothing> = Completed
    }

    fun isLoading(): Boolean {
        return this is Loading
    }

    fun isSuccess(): Boolean {
        return this is Success<*>
    }

    fun isFailure(): Boolean {
        return this is Failure
    }

    fun getSuccess(): T? {
        return (this as? Success<T>)?.data
    }

    fun getFailure(): Failure? {
        return this as? Failure
    }

    infix fun onLoading(f: (Loading) -> Unit): AppResult<T> =
        when (this) {
            is Loading -> {
                f(this)
                this
            }
            is Success -> this
            is Failure -> this
            is Completed -> this
        }

    infix fun onSuccess(f: (T) -> Unit): AppResult<T> =
        when (this) {
            is Success -> {
                f(this.data)
                this
            }
            is Loading -> this
            is Failure -> this
            is Completed -> this
        }

    infix fun onFailure(f: (Failure) -> Unit) =
        if (this is Failure) f(this) else Unit
}
