package com.malky.bostatask.utils

/**
 * Representing the result of an operation that can either succeed with data [D]
 * or fail with an error [E] which must be a subtype of [ErrorType].
 *
 * @param D The type of data returned on success.
 * @param E The type of error returned on failure.
 */
sealed interface Result<out D, out E : ErrorType> {
    /**
     * Represents a successful operation containing the resulting [data].
     */
    data class Success<D>(val data: D) : Result<D, Nothing>

    /**
     * Represents a failed operation containing the [error] details.
     */
    data class Error<out E : ErrorType>(val error: E) : Result<Nothing, E>
}

/**
 * Executes the given [action] if the [Result] is [Result.Success] else returns the original [Result] to allow for chaining.
 *
 * @param action Lambda to execute with the successful data.
 * @return The original [Result] instance.
 */
inline fun <D, E : ErrorType> Result<D, E>.onSuccess(action: (D) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

/**
 * Executes the given [action] if the [Result] is [Result.Error] else returns the original [Result] to allow for chaining.
 *
 * @param action Lambda to execute with the error details.
 * @return The original [Result] instance.
 */
inline fun <D, E : ErrorType> Result<D, E>.onError(action: (E) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}

/**
 * Transforms the data in a [Result.Success] using the provided [map] function.
 * If the [Result] is an [Result.Error], it returns the error unchanged.
 *
 * @param map Function to transform the success data.
 * @return A new [Result] containing either the transformed data or the original error.
 */
inline fun <D, E : ErrorType, R> Result<D, E>.map(map: (D) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(data = map(data))
    }
}
