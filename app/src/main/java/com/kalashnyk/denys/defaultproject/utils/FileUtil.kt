package com.kalashnyk.denys.defaultproject.utils


import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.kalashnyk.denys.defaultproject.App

import org.apache.commons.lang3.StringUtils

import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.DecimalFormat
import java.util.Arrays
import java.util.Collections
import java.util.Locale
import java.util.Objects

import timber.log.Timber

object FileUtil {

    val imagesContentDir: File
        get() {
            val file = File(
                Environment.getExternalStorageDirectory().toString() + "/SC/images"
            )
            if (!file.exists()) {
                createMediaContentFolder()
            }
            return file
        }

    val videoContentDir: File
        get() {
            val file = File(
                Environment.getExternalStorageDirectory().toString() + "/SC/video"
            )
            if (!file.exists()) {
                createMediaContentFolder()
            }
            return file
        }

    fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) {
            for (child in fileOrDirectory.listFiles()!!) {
                deleteRecursive(child)
            }
        }

        fileOrDirectory.delete()
    }

    fun toByteArray(file: File): ByteArray? {
        var ous: ByteArrayOutputStream? = null
        var ios: InputStream? = null
        try {
            val buffer = ByteArray(4096)
            ous = ByteArrayOutputStream()
            ios = FileInputStream(file)
            var read: Int
            while ((read = ios.read(buffer)) != -1)
                ous.write(buffer, 0, read)
        } catch (e: Exception) {
            return null
        } finally {
            try {
                ous?.close()
            } catch (ignored: IOException) {
            }

            try {
                ios?.close()
            } catch (ignored: IOException) {
            }

        }

        return ous!!.toByteArray()
    }

    @Throws(IOException::class)
    fun copyFile(src: File, dst: File) {
        val inputStream = FileInputStream(src)
        try {
            val out = FileOutputStream(dst)
            try {
                val buf = ByteArray(1024)
                var len: Int
                while ((len = inputStream.read(buf)) > 0) {
                    out.write(buf, 0, len)
                }
            } finally {
                closeSilently(out)
            }
        } finally {
            closeSilently(inputStream)
        }
    }

    fun storeContent(
        inputStream: InputStream, path: String,
        fileExtension: String
    ): File {
        val directory = File(path)
        directory.mkdirs()

        val filename = String.format(
            Locale.US, "%d%s",
            System.currentTimeMillis(), fileExtension
        )
        val storedContent = File(directory, filename)

        var outputStream: BufferedOutputStream? = null

        try {
            outputStream = BufferedOutputStream(
                FileOutputStream(storedContent), Util.IO_BUFFER_SIZE
            )
            // read buffer of 2048 bytes
            val buf = ByteArray(2048)
            var len: Int
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len)
            }
            outputStream.flush()
        } catch (e: IOException) {
            Timber.d(e)
        } catch (e: NullPointerException) {
            Timber.d(e)
        } finally {
            closeSilently(inputStream)
            closeSilently(outputStream)
        }
        return storedContent
    }

    fun closeSilently(closeable: Closeable?) {
        if (closeable == null) {
            return
        }

        try {
            closeable.close()
        } catch (ignored: Exception) {
        }

    }

    fun getUriFromPath(uri: Uri, context: Context): Uri {
        try {
            return Uri.parse(getPath(uri, context))
        } catch (e: Exception) {
            e.printStackTrace()
            return uri
        }

    }

    private fun getPath(uri: Uri, context: Context): String? {

        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        try {
            val storage = File(
                Objects.requireNonNull(uri.path)
            ).canonicalPath
                .split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val i = Collections.disjoint(
                Arrays.asList("external", "external_files", "storage"),
                Arrays.asList(*Arrays.copyOf(storage, 3))
            )
            if (i) {
                Timber.d("Illegal or incorrect uri :%s", uri)
                return ""
            }
        } catch (e: SecurityException) {
            Timber.e(e)
            return ""
        } catch (e: IOException) {
            Timber.e(e)
            return ""
        } catch (e: NullPointerException) {
            Timber.e(e)
            return ""
        }

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }

            } else if (isDownloadsDocument(uri)) {

                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )

                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }

                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])

                return getDataColumn(context, contentUri, selection, selectionArgs)
            }

        } else if ("content".equals(uri.scheme!!, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    private fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {

        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    private fun getContentMimeType(contentUri: Uri): String? {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(contentUri.toString())
        } catch (e: RuntimeException) {
            Timber.e("Get Content MimeType failed")
        }

        var mimeType: String? = retriever
            .extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
        if (StringUtils.isBlank(mimeType)) {
            val extension = MimeTypeMap
                .getFileExtensionFromUrl(contentUri.toString())
            if (extension != null) {
                mimeType = MimeTypeMap.getSingleton()
                    .getMimeTypeFromExtension(extension)
            }
        }
        retriever.release()
        return mimeType
    }

    fun isImageContent(contentUri: Uri): Boolean {
        val type = getContentMimeType(contentUri)
        return type != null && type.startsWith("image")
    }

    fun isVideoContent(contentUri: Uri): Boolean {
        val type = getContentMimeType(contentUri)
        return type != null && type.startsWith("video")
    }

    fun getFileSize(file: File): Float {
        return file.length().toFloat()
    }

    fun getFileSizeInMB(file: File): Float {
        return (file.length() / 1024 / 1024).toFloat()
    }

    fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    fun getFileSizeWithUnits(size: Long): String {
        if (size <= 0)
            return "0"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(
            size / Math.pow(1024.0, digitGroups.toDouble())
        ) + " " + units[digitGroups]
    }

    fun deleteFile(filePath: String): Boolean {
        return StringUtils.isEmpty(filePath) || File(filePath).delete()
    }

    fun deleteIfTempFile(filePath: String) {
        if (StringUtils.isNotEmpty(filePath) && Util.isFileInTempDir(
                App.getAppContext(), filePath
            )
        ) {
            deleteFile(filePath)
        }
    }

    fun createMediaContentFolder() {
        val mediaPath = File(
            Environment.getExternalStorageDirectory().toString() + "/SC"
        )
        if (!mediaPath.exists()) {
            mediaPath.mkdir()
        }
        val filePathImage = File(
            Environment.getExternalStorageDirectory().toString() + "/SC/images"
        )
        val filePathVideo = File(
            Environment.getExternalStorageDirectory().toString() + "/SC/video"
        )
        if (!filePathImage.exists()) {
            filePathImage.mkdir()
        }
        if (!filePathVideo.exists()) {
            filePathVideo.mkdir()
        }
    }

    fun resolveFilePath(filePath: String): String {
        var filePath = filePath
        if (filePath.contains("%20")) {
            filePath = Uri.decode(filePath)
        }
        return filePath.replace("file://", "")
    }
}
