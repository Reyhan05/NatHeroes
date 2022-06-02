package com.project.natheroes.service

import com.project.HeroesResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("heroes")
    fun getHeroes(): Flowable<ArrayList<HeroesResponse>>

    @GET("heroes")
    fun getSearchHero(
        @Query("q") query: String
    )
}
