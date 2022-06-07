package com.project.natheroes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.natheroes.response.HeroesGirlsResponse
import com.project.natheroes.response.HeroesResponse
import com.project.natheroes.service.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()

    var sejarahHeroes = MutableLiveData<ArrayList<HeroesResponse>>()
    var heroesGirls = MutableLiveData<ArrayList<HeroesGirlsResponse>>()

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

    fun getHeroesGirls(
        responHandler: (ArrayList<HeroesGirlsResponse>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClient.getApiService().getHeroesGirls().subscribeOn(Schedulers.io())
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

    fun getHeroesGirls() {
        isLoading.value = true
        getHeroesGirls({
            isLoading.value = false
            it.drop(57)
            heroesGirls.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}