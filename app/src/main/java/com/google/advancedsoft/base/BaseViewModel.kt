package com.google.advancedsoft.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.advancedsoft.network.AppResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    open suspend fun <T> execute(apiCallBlock: suspend () -> T): AppResult<T> {
        return try {
            AppResult.success(apiCallBlock.invoke())
        } catch (t: Throwable) {
            AppResult.error()
        }
    }

    fun <T> executeAndNotify(
        liveData: MutableLiveData<AppResult<T>>,
        enableCompletedEvent: Boolean = false,
        resetDataAfterSubmit: Boolean = false,
        apiCallBlock: suspend (context: CoroutineScope) -> T
    ): Job {
        liveData.postValue(AppResult.loading())
        return viewModelScope.launch(Dispatchers.IO) {
            val result = execute { apiCallBlock.invoke(this) }
            launch(Dispatchers.Main) {
                liveData.value = result
                if (enableCompletedEvent) {
                    liveData.value = AppResult.Completed
                }
                if (resetDataAfterSubmit) {
                    liveData.value = null
                }
            }
        }
    }
}