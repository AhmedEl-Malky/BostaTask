package com.malky.bostatask.utils

sealed interface DataErrors : ErrorType {
    enum class Remote(val message: String) : DataErrors {
        REQUEST_TIMEOUT(message = "Request timeout"),
        TOO_MANY_REQUESTS(message = "Too many requests"),
        NO_INTERNET(message = "There is no internet connection"),
        SERVER(message = "Server error"),
        SERIALIZATION(message = "Serialization Error"),
        UNAUTHORIZED(message = "Unauthorized , Check API Key"),
        UNKNOWN(message = "Unknown error has occurred"),
        NOT_FOUND(message = "Not Found")
    }


    enum class Local(val message: String) : DataErrors {
        DISK_IO_ERROR(message = "Failed to access local storage."),
        DATABASE_CORRUPT(message = "The local data is corrupted. Please try clearing the app data."),
        QUERY_FAILED(message = "The query failed"),
        UNKNOWN(message = "Unknown error has occurred")
    }
}