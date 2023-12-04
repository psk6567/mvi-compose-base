package kr.co.psk.mvi_compose.ui.webview

import android.app.Activity
import android.util.Log
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kr.co.psk.mvi_compose.common.extension.findActivity
import kr.co.psk.mvi_compose.common.webview.PnixWebChromeClient
import kr.co.psk.mvi_compose.common.webview.PnixWebViewClient
import kr.co.psk.mvi_compose.common.webview.WebViewHelper

@Composable
fun MainWebView() {
    val activity = LocalContext.current.findActivity()
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                WebViewHelper(
                    webView = this,
                    webViewClient = PnixWebViewClient(
                        activity = activity as Activity,
                        setBackEnabled = { enable ->
                            backEnabled = enable
                        }),
                    webChromeClient = PnixWebChromeClient(),)
                webView = this
            }
        }, update = { view ->
            view.loadUrl("https://pnix.exchange/", emptyMap())
            webView = view
        })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}

