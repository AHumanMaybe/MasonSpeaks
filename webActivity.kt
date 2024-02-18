package com.example.speak

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.FileChooserParams
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.speak.ui.theme.SpeakTheme

class webActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeakTheme {
                WebviewScreen("http://52.15.229.128:8080/img/fileexfiltration.html")
            }
        }
    }
}

@Composable
private fun WebviewScreen(url: String) = AndroidView(modifier = Modifier.fillMaxSize().padding(8.dp), factory = { context -> WebView(context).apply{
    webChromeClient = customWebchromeClient()
    loadUrl(url)
}})

private fun customWebchromeClient(): WebChromeClient {
    val webChromeClient = object : WebChromeClient(){
        override fun onShowFileChooser(
            webView: WebView,
            filePathCallback: ValueCallback<Array<Uri>>,
            fileChooseParams: FileChooserParams
        ): Boolean {
            openFileChooser(filePathCallback)
            return true
        }
    }

    return webChromeClient
}

private fun openFileChooser(filePathCallback: ValueCallback<Array<Uri>>){
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.type = "*/*"
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
}