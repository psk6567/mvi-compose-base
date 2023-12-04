package kr.co.psk.mvi_compose.common.webview

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URISyntaxException

class WebViewHelper(val webView: WebView, webViewClient: WebViewClient, webChromeClient: WebChromeClient,) {
    init {
        webView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            fitsSystemWindows = true
            with(settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                textZoom = 100 // system 글꼴 크기에 의해 변하는 것 방지
                setSupportMultipleWindows(true) // 새 창(Blank) 띄우기
            }
            this.webViewClient = webViewClient
            this.webChromeClient = webChromeClient
        }
    }
}

class PnixWebChromeClient(): WebChromeClient() {
    override fun onCreateWindow(
        view: WebView,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        val result = view.hitTestResult // 웹뷰에서 클릭된 정보 획득
        val url = result.extra // url 추출
        url?.let {  // 새 창(Blank) 띄우기
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            view.context.startActivity(browserIntent)
        }
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

}

class PnixWebViewClient(private val activity: Activity, private val setBackEnabled: (Boolean) -> Unit) : WebViewClient() {
    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        setBackEnabled(view.canGoBack())
//        Log.e("test check onPageStarted", "${url}")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        val javascript =
            "document.body.style.webkitTapHighlightColor = 'rgba(0, 0, 0, 0)';" + // 클릭 박스 색깔 투명화
                    "document.body.style.webkitUserSelect = 'none';" +
                    "document.body.style.webkitTouchCallout = 'none';"
        view?.evaluateJavascript(javascript, null) // 로드된 페이지에만 적용 가능
//        Log.e("test check onPageFinished", "${url}")
    }

    override fun onReceivedSslError(
        view: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        super.onReceivedSslError(view, handler, error)
//        Log.e("test check onReceived", "${error}")
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//        Log.e("test check should~", "${request.url} ${request.requestHeaders}")
        return canUrlLoading(view = view, url = request.url.toString())
    }

    fun canUrlLoading(view: WebView, url: String): Boolean =
        url.let {
            if (!URLUtil.isNetworkUrl(url) && !URLUtil.isJavaScriptUrl(url)) {
                val uri = try {
                    Uri.parse(url)
                } catch (e: Exception) {
                    return false
                }

                return when (uri.scheme) {
                    "intent" -> {
                        startSchemeIntent(url)
                    }
                    else -> {
                        return try {
                            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
                            true
                        } catch (e: Exception) {
                            false
                        }
                    }
                }
            } else {
                return false
            }
        }

    /*Intent 스킴을 처리하는 함수*/
    fun startSchemeIntent(url: String): Boolean {
        val schemeIntent: Intent = try {
            Intent.parseUri(url, Intent.URI_INTENT_SCHEME) // Intent 스킴을 파싱
        } catch (e: URISyntaxException) {
            return false
        }
        try {
            activity.startActivity(schemeIntent) // 앱으로 이동
            return true
        } catch (e: ActivityNotFoundException) { // 앱이 설치 안 되어 있는 경우
            val packageName = schemeIntent.getPackage()

            if (!packageName.isNullOrBlank()) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName") // 스토어로 이동
                    )
                )
                return true
            }
        }
        return false
    }
}