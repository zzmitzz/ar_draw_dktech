package com.dktech.baseandroidviewdktech.utils.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit


suspend fun Context.getImageFromCache(fileName: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        val file = File(cacheDir, fileName)
        if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }
}


suspend fun Context.saveImageToCache(
    url: String?,
    fileName: String,
    onSuccess: (String) -> Unit = {},
    onFailure: (String) -> Unit = {}
): Unit {
    withContext(Dispatchers.IO) {
        if (url == null) {
        } else {
            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            try {
                val response = client.newCall(request).execute()

                if (!response.isSuccessful) {
                    throw IOException("HTTP error: ${response.message}")
                }

                val body = response.body ?: throw IOException("Response body is null")
                val totalSize = body.contentLength()

                if (totalSize <= 0) throw IOException("Invalid content length")

                val file = File(cacheDir, fileName)
                val input = body.byteStream()
                val output = FileOutputStream(file)

                val buffer = ByteArray(64 * 1024)
                var downloaded = 0L
                var read: Int

                while (input.read(buffer).also { read = it } != -1) {

                    output.write(buffer, 0, read)
                    downloaded += read

                    val progress = ((downloaded * 100L) / totalSize).toInt()
                }

                output.flush()
                output.close()
                input.close()

                Log.d("saveImageToCache", "Image saved to cache: ${file.absolutePath}")
            } catch (e: Exception) {
                Log.d("saveImageToCache", "Download failed: ${e.message}")
            }
        }
    }
}