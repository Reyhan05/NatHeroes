package com.project.natheroes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.natheroes.response.HeroesResponse
import com.project.natheroes.service.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()

    var sejarahHeroes = MutableLiveData<ArrayList<HeroesResponse>>()

    fun getData(
        responHandler: (ArrayList<HeroesResponse>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClient.getApiService().getHeroes().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getHeroess() {
        isLoading.value = true
        getData({
            isLoading.value = false
            it.drop(57)
            sejarahHeroes.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}