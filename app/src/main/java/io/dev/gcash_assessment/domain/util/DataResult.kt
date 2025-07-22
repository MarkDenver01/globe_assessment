package io.dev.gcash_assessment.domain.util

sealed class DataResult<out T> {
    object Loading : DataResult<Nothing>()
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String): DataResult<Nothing>()
}