package com.project.natheroes.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroesResponse(

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("death_year")
    val deathYear: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("ascension_year")
    val ascensionYear: Int? = null,

    @field:SerializedName("birth_year")
    val birthYear: Int? = null
) : Parcelable
