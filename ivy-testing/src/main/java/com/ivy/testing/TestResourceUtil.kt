package com.ivy.testing

import java.io.File
import java.io.FileInputStream

fun testResourceInputStream(resPath: String): FileInputStream {
    try {
        val file = testResource(resPath)
        return FileInputStream(file)
    } catch (e: Exception) {
        throw TestResourceLoadException(resPath, e)
    }
}

fun testResource(resPath: String): File {
    try {
        val classLoader = Thread.currentThread().contextClassLoader
        val fileUrl = classLoader!!.getResource(resPath)
            ?: throw TestResourceLoadException(resPath, message = "The fileUrl is null!")
        return File(fileUrl.path)
    } catch (e: Exception) {
        throw TestResourceLoadException(resPath, e)
    }
}

class TestResourceLoadException(
    resPath: String,
    e: Throwable? = null,
    message: String = "Check your test setup.",
) : Exception("Could not load \"$resPath\" test resource. $message", e)