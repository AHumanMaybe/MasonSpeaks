package com.example.speak

import android.graphics.Bitmap
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface calllocations {
    @GET("/getstory2f22d7d5")
    suspend fun getloca(@Query("long") long:Double, @Query("lat") lat:Double):Response<List<locals>>

    @Headers("Accept: application/json")
    @POST("/createstoryb59a1937")
    suspend fun createPost(
        @Body data: postdata
    ):Response<postdata>

}