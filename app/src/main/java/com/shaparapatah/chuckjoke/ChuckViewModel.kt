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


    fun sendServerRequest() {
        liveDataToObserve.postValue(AppState.Loading)
        val joke = "some text"
        if (joke.isBlank()) {
            AppState.Error(Throwable("Empty string"))
        } else {
            retrofitImpl.getRetrofitImpl().getChuckJoke().enqueue( //FIXME ошибка1
                object : Callback<List<Value>> {
                    override fun onResponse(
                        call: Call<List<Value>>,
                        response: Response<List<Value>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataToObserve.postValue(AppState.Success(response.body() as Value))
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

                    override fun onFailure(call: Call<List<Value>>, t: Throwable) {
                        liveDataToObserve.value = AppState.Error(t)
                    }

                }
            )
        }
    }
}