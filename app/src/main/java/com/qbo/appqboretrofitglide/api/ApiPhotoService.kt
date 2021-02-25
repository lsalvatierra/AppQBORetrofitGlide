package com.qbo.appqboretrofitglide.api

import com.qbo.appqboretrofitglide.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface ApiPhotoService {

    @GET("photos")
    fun getAllPhotos(): Call<List<Photo>>
}