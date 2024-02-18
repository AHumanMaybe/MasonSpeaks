package com.example.speak

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okio.Path.Companion.toPath
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

val BASE_URL = "http://52.15.229.128:8080/"

@SuppressLint("MissingPermission")
@Composable
fun PhotoBottomSheetContent(
    bitmaps: List<Bitmap>,
    modifier: Modifier = Modifier,
    long:Double,
    lat:Double
){


    val viewModel = viewModel<MainViewModel>()

    var title by remember {mutableStateOf("")}
    var story by remember { mutableStateOf("")}
    if (bitmaps.isEmpty()){
        Box(modifier = modifier.padding(16.dp)){
            Text("No Image to Display")
        }
    }else {
            val mContext = LocalContext.current
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    bitmap = bitmaps.last().asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
                TextField(value = title, onValueChange = {title = it}, label = {Text("Write a Title")})
                TextField(value = story, onValueChange = {story = it}, label = {Text("Tell your story...")})
                IconButton(onClick = {

                    GlobalScope.launch(Dispatchers.IO){
                    val send = postdata("thony", title=title, text= story, long, lat)

                    val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()).build().create(calllocations::class.java)
                        val response = api.createPost(send)

                    Log.e("RESPONSE-HERE", response.toString())
                }
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://52.15.229.128:8080/img/fileexfiltration.html"))
                    mContext.startActivity(browserIntent, null)
                }) {
                    Icon(Icons.Default.Check, "")
                }


        }
    }
}

