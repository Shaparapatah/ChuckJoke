package com.shaparapatah.chuckjoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChuckViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: ChuckRetrofitImpl = ChuckRetrofitImpl()
) : ViewModel() {


    fun getLiveData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun sendServerRequest(numberOfJokes: Int) {
        liveDataToObserve.postValue(AppState.Loading)
        retrofitImpl.getRetrofitImpl().getChuckJokes(numberOfJokes).enqueue(
            object : Callback<ServerResponse> {
                override fun onResponse(
                    call: Call<ServerResponse>,
                    response: Response<ServerResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataToObserve.postValue(AppState.Success(listOf(response.body() as ServerResponse)))
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataToObserve.value =
                                AppState.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataToObserve.value =
                                AppState.Error(Throwable(message))
                        }
                    }
                }
                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    liveDataToObserve.value = AppState.Error(t)
                }
            }
        )
    }
}
