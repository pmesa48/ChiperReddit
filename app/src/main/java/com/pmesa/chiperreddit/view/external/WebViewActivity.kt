package com.pmesa.chiperreddit.view.external

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.pmesa.chiperreddit.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webview?.settings?.javaScriptEnabled = true
        webview.loadUrl(intent.getStringExtra(URL))
    }

    companion object{
        val URL = "URL"
    }
}
