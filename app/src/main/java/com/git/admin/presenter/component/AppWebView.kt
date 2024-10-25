package com.git.admin.presenter.component

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.kevinnzou.web.AccompanistWebViewClient
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState
import com.git.admin.util.AppLogger

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AppWebView(
    modifier: Modifier = Modifier,
    url: String,
    redirectedTo: (String) -> Unit = {}
) {
    var initialUrl by remember { mutableStateOf(url) }
    LaunchedEffect(url) {
        initialUrl = url
    }

    val state = rememberWebViewState(url = initialUrl)
    val navigator = rememberWebViewNavigator()

    val webClient = remember {
        object : AccompanistWebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                AppLogger.logD("[AppWebView] onPageStarted: $url")
            }

            override fun onPageFinished(view: WebView, url: String?) {
                super.onPageFinished(view, url)
                AppLogger.logD("[AppWebView] onPageFinished: $url")
                redirectedTo(url ?: "")
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                AppLogger.logE("[AppWebView] onReceivedError: $error")
            }
        }
    }

    WebView(
        state = state,
        modifier = modifier.fillMaxSize(),
        navigator = navigator,
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
        },
        client = webClient
    )
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppYesNoButtonsPreview() {
    AppWebView(url = "https://demo.docusign.net/Signing/MTRedeem/v1/d5b28bd3-94cf-4f54-bb96-68b6e177e874?slt=eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQYAAAABAAMABwAAjts6wMTcSAgAAC7tweHE3EgYAAEAAAAAAAAAIQDrAgAAeyJUb2tlbklkIjoiYzMyZjkwZDAtOGYyMy00MzZhLWFlNTctN2Q1MDgxZGRkZjAxIiwiRXhwaXJhdGlvbiI6IjIwMjQtMDgtMjVUMDQ6NDY6NDcrMDA6MDAiLCJJc3N1ZWRBdCI6IjIwMjQtMDgtMjVUMDQ6NDE6NDguMDU1Nzk5KzAwOjAwIiwiUmVzb3VyY2VJZCI6IjQ0OWFhOTBjLTI2MGYtNGY5MC05OTM5LWI1NTdlNjY2YTcxOCIsIlJlc291cmNlcyI6IntcIkVudmVsb3BlSWRcIjpcIjQ0OWFhOTBjLTI2MGYtNGY5MC05OTM5LWI1NTdlNjY2YTcxOFwiLFwiQWN0b3JVc2VySWRcIjpcIjMwNjljODcxLTkzNWQtNGMyOS1iMTgzLWM0MDc2NWJlOGU4MVwiLFwiUmVjaXBpZW50SWRcIjpcIjYzMGU1YTFhLTIyYjMtNDg0OC05ZThjLWJkYzA1ZDljN2Y4MlwiLFwiRmFrZVF1ZXJ5U3RyaW5nXCI6XCJ0PTUwN2U1YzQwLTk3NDAtNDI3Yy1hYzhhLTllN2YxNmFhMWJmZlwiLFwiSW50ZWdyYXRvcktleVwiOlwiNzRhZDZhOWItODM4Ni00MjlhLThkZjUtNjA3OWY1Y2RmZGViXCIsXCJDcmVhdGVkQXRcIjpcIjIwMjQtMDgtMjVUMDQ6NDE6NDcuOTk5MDAxMVpcIn0iLCJUb2tlblR5cGUiOjEsIkF1ZGllbmNlIjoiMjVlMDkzOTgtMDM0NC00OTBjLThlNTMtM2FiMmNhNTYyN2JmIiwiUmVkaXJlY3RVcmkiOiJodHRwczovL2RlbW8uZG9jdXNpZ24ubmV0L1NpZ25pbmcvU3RhcnRJblNlc3Npb24uYXNweCIsIkhhc2hBbGdvcml0aG0iOjAsIkhhc2hSb3VuZHMiOjAsIlRva2VuU3RhdHVzIjowLCJJc1NpbmdsZVVzZSI6ZmFsc2V9PwAAv3rswMTcSA.kNgR0zHrYPNIvBdDlw6LHuQItu4aOOIHpYe7RqRdq0nKOeg_xfNuJ9XYxHPM8znTRpD23uNQW3N6SoCjzNfuWf3yF0YQxCrWSpIGGxb03rmg7h80Om-4KzQ5pvIQKCIK1n07UAqfRAbS53NJkoz0DYJbW7s4mCchSubz3tSJmEmQDVUfznhwvCEWTSUVao2yFOKPjv7ACEeYq2SMPBM3Fc-LdySd4PaxGKrqurSKkFzT6eyz7SAVxPapTcd6JBoyZo14fW4vk6HamLFGG6VQNeuB9-h7H9uhPOqcXXJo_GgbhV05GfJmj5v1G7Nm1oWwqajF1Acf_CjQwTmgQ7LR1g")
}