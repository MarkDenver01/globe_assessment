package io.dev.globe_assessment.domain.util

/**
 * A generic sealed class that represents the state of data being handled.
 *
 * This is commonly used to wrap responses or operations that can either be:
 * - loading
 * - successful with data
 * - failed with an error message
 */
sealed class DataResult<out T> {

    /**
     * Represents a loading state.
     */
    object Loading : DataResult<Nothing>()

    /**
     * Represents a successful state with data of type [T].
     *
     * @param data The data returned from the operation.
     */
    data class Success<T>(val data: T) : DataResult<T>()

    /**
     * Represents an error state with a message.
     *
     * @param message The error message explaining the failure.
     */
    data class Error(val message: String) : DataResult<Nothing>()
}
