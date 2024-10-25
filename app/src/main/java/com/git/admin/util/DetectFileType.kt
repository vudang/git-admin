package com.git.admin.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

enum class UrlType {
    PHOTO, PDF, UNKNOWN
}

suspend fun getUrlType(urlString: String): UrlType = withContext(Dispatchers.IO) {
    try {
        // First, check the file extension
        val extension = urlString.substringAfterLast('.', "").lowercase()
        when (extension) {
            "jpg", "jpeg", "png", "gif", "bmp", "webp" -> return@withContext UrlType.PHOTO
            "pdf" -> return@withContext UrlType.PDF
        }

        // If the extension doesn't give a clear answer, check the Content-Type
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "HEAD"
        connection.connect()

        val contentType = connection.contentType.lowercase()
        return@withContext when {
            contentType.startsWith("image/") -> UrlType.PHOTO
            contentType == "application/pdf" -> UrlType.PDF
            else -> UrlType.UNKNOWN
        }
    } catch (e: Exception) {
        println("Error detecting URL type: ${e.message}")
        UrlType.UNKNOWN
    }
}