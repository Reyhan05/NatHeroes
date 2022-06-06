package com.project.natheroes.service

import com.project.natheroes.response.HeroesResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("heroes")
    fun getHeroes(): Flowable<ArrayList<HeroesResponse>>

    @GET("heroes_search")
    fun getSearchHeroesByName(): Flowable<ArrayList<HeroesResponse>>
}
