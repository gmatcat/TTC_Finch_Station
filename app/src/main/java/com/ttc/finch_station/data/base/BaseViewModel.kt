package com.ttc.finch_station.data.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    override fun onCleared(){
        super.onCleared()
        viewModelScope.cancel()
    }
}