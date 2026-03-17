package com.malky.bostatask.data.utils

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteDiskIOException
import androidx.sqlite.SQLiteException
import com.malky.bostatask.utils.DataErrors
import com.malky.bostatask.utils.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.nio.channels.UnresolvedAddressException

/**
 * Executes a remote network request and maps the response or any thrown exceptions
 * to a [Result] of either the expected type [T] or a [DataErrors.Remote] error.
 *
 * @param T The expected type of the response body.
 * @param execute A lambda that performs the actual Retrofit network call.
 * @return A [Result.Success] containing the body if successful, or [Result.Error] with a specific [DataErrors.Remote].
 */
suspend inline fun <reified T> request(
    execute: () -> Response<T>
): Result<T, DataErrors.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataErrors.Remote.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        return Result.Error(DataErrors.Remote.NO_INTERNET)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataErrors.Remote.NO_INTERNET)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        return Result.Error(DataErrors.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

/**
 * Converts a Retrofit [Response] into a [Result] object.
 * Handles successful responses (2xx) by extracting the body, and maps common HTTP error
 * codes to their corresponding [DataErrors.Remote] types.
 *
 * @param T The expected type of the response body.
 * @param response The Retrofit response object to be converted.
 * @return A [Result.Success] with the body, or [Result.Error] based on the HTTP status code.
 */
suspend inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, DataErrors.Remote> {
    return when (response.code()) {
        in 200..299 -> {
            try {
                Result.Success(
                    response.body() ?: return Result.Error(
                        DataErrors.Remote.SERIALIZATION
                    )
                )
            } catch (e: SerializationException) {
                Result.Error(DataErrors.Remote.SERIALIZATION)
            }
        }

        408 -> Result.Error(DataErrors.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataErrors.Remote.TOO_MANY_REQUESTS)
        404 -> Result.Error(DataErrors.Remote.NOT_FOUND)
        in 500..599 -> Result.Error(DataErrors.Remote.SERVER)
        else -> Result.Error(DataErrors.Remote.UNKNOWN)
    }
}

/**
 * Executes a local database query or action and wraps it in a [Result].
 * Catches common [SQLiteException]s and maps them to [DataErrors.Local].
 *
 * @param T The type of the result returned by the database action.
 * @param action A suspend lambda containing the database operation to execute.
 * @return A [Result.Success] containing the action's result, or [Result.Error] with a [DataErrors.Local] if a database error occurs.
 */
suspend inline fun <T> query(
    action: suspend () -> T
): Result<T, DataErrors.Local> {
    return try {
        Result.Success(action())
    } catch (e: SQLiteDiskIOException) {
        Result.Error(DataErrors.Local.DISK_IO_ERROR)
    } catch (e: SQLiteDatabaseCorruptException) {
        Result.Error(DataErrors.Local.DATABASE_CORRUPT)
    } catch (e: SQLiteConstraintException) {
        Result.Error(DataErrors.Local.QUERY_FAILED)
    } catch (e: SQLiteException) {
        Result.Error(DataErrors.Local.UNKNOWN)
    } catch (e: java.lang.Exception) {
        Result.Error(DataErrors.Local.UNKNOWN)
    }
}
