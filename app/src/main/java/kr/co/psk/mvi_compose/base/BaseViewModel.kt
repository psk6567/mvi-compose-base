package kr.co.psk.mvi_compose.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.supervisorScope
import kr.co.psk.common.di.IoDispatcher
import kr.co.psk.common.di.MainDispatcher
import javax.inject.Inject

abstract class BaseViewModel(
    val context: Context,
) : ViewModel()
{
    protected fun ioScope(inScopeAction: suspend () -> Unit) {
        val ioScopeJob = viewModelScope + SupervisorJob()
        ioScopeJob.launch(Dispatchers.IO){
            inScopeAction()
        }
    }

    open fun onResume() {

    }

    open fun onPause() {

    }
}