package com.example.speak

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class FileRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun uploadImage(file: File){
        return try{
            FileAPI.instance.createImage(
                image = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
            )
        }catch(e: IOException){
            Log.e("FILEREPO", e.printStackTrace().toString())
            e.printStackTrace()
        }catch(e: HttpException){
            Log.e("FILEREPO", e.printStackTrace().toString())
            e.printStackTrace()
        }
    }

}