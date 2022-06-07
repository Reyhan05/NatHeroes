package com.project.natheroes.ui

import com.project.natheroes.response.HeroesGirlsResponse
import com.project.natheroes.response.HeroesResponse

interface OnItemClickCallback {

    fun onItemClicked(data: HeroesResponse)
    fun onItemClicked(data: HeroesGirlsResponse)

}
