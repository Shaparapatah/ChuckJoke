package com.shaparapatah.chuckjoke.view.viewModel

import com.shaparapatah.chuckjoke.ServerResponse


sealed class AppState {
        data class Success(val serverResponseData: List<ServerResponse>) : AppState()
        data class Error(val error: Throwable) : AppState()
        object Loading : AppState()
}
