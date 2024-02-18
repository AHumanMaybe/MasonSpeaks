package com.example.speak

import com.google.android.gms.common.api.Response
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileAPI {
    @Multipart
    @POST("/uploadimage696467a7")
    suspend fun createImage(
        @Part image: MultipartBody.Part
    )

    companion object {
        val instance by lazy {
            Retrofit.Builder().baseUrl("http://52.15.229.128:8080/").build().create(FileAPI::class.java)
        }
    }
}