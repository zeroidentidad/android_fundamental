package com.example.awebviewtemplate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ref: https://developer.android.com/guide/webapps/webview
        // this as activityContext
        val myWebView = WebView(this)
        setContentView(myWebView)
        myWebView.settings.javaScriptEnabled = true
        myWebView.addJavascriptInterface(WebAppInterface(this), "Android")
        myWebView.loadUrl("https://www.example.com")
    }
}

/** Instantiate the interface and set the context  */
class WebAppInterface(private val mContext: Context) {
    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}

// in the website file
/*
    <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />

    <script type="text/javascript">
        function showAndroidToast(toast) {
            Android.showToast(toast);
        }
    </script>
*/