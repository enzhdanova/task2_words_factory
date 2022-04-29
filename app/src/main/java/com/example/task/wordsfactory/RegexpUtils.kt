package com.example.task.wordsfactory

object RegexpUtils {
    const val REGEXP_NAME = """^[a-zA-Z][a-zA-Z0-9_]{4,14}$"""
    const val REGEXP_EMAIL =
        """^[a-zA-Z][a-zA-Z0-9_\.]{4,30}\@[a-zA-Z][a-zA-Z0-9]{3,14}\.[a-zA-Z][a-zA-Z0-9]{1,4}$"""
    const val REGEXP_PASSWORD = """^[a-zA-Z0-9]{8,30}"""

    fun correct(regex: Regex, text: String): Boolean {
        return text.matches(regex)
    }

    fun correct(regex: String, text: String): Boolean {
        return text.matches(regex.toRegex())
    }
}