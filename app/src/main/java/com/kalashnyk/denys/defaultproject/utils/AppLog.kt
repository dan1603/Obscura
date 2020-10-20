package com.kalashnyk.denys.defaultproject.utils

import android.util.Log
import com.kalashnyk.denys.defaultproject.BuildConfig

import java.util.HashSet
import java.util.regex.Matcher
import java.util.regex.Pattern

object AppLog {
    private val CLASSNAME_TO_ESCAPE = escapedClassNames
    private val INCLUDE_METHOD = BuildConfig.DEBUG
    private const val LINE_PREFIX = "APP:"
    private const val MAX_TAG_LENGTH = 50
    private const val PACKAGE_PREFIX = BuildConfig.APPLICATION_ID + "."
    private val COMPILE = Pattern.compile(PACKAGE_PREFIX, Pattern.LITERAL)

    private val callingMethod: String?
        get() {
            val stacks = Thread.currentThread().stackTrace
            if (stacks != null) {
                for (stack in stacks) {
                    val cn = stack.className
                    if (cn != null && !CLASSNAME_TO_ESCAPE.contains(cn)) {
                        return if (INCLUDE_METHOD) {
                            cn + "#" + stack.methodName
                        } else {
                            cn
                        }
                    }
                }
            }
            return null
        }

    private val escapedClassNames: Set<String>
        get() {
            val set = HashSet<String>()

            set.add("java.lang.Thread")
            set.add("dalvik.system.VMStack")
            set.add(Log::class.java.name)
            set.add(AppLog::class.java.name)
            return set
        }

    fun d(message: String) {
        dInternal(message)
    }

    private fun dInternal(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(calcTag(), calcMessage(message))
        }
    }

    private fun calcTag(): String {
        val caller = callingMethod
        return if (caller == null) {
            ""
        } else {
            val shortTag = COMPILE.matcher(caller).replaceAll(Matcher.quoteReplacement(""))
            val shouldBeShorter = shortTag.length > MAX_TAG_LENGTH

            if (shouldBeShorter) {
                val length = shortTag.length
                val start = length - MAX_TAG_LENGTH
                shortTag.substring(start, length)
            } else {
                shortTag
            }
        }
    }

    private fun calcMessage(message: String): String {
        return LINE_PREFIX + message
    }
}