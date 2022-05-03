package com.example.exampleapp.presentation.common.model

class ResourceState<T> private constructor(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?): ResourceState<T> {
            return ResourceState(
                Status.SUCCESS,
                data,
                null
            )
        }
        fun <T> error(message: String): ResourceState<T> {
            return ResourceState(
                Status.ERROR,
                null,
                message
            )
        }
        fun <T> loading(): ResourceState<T> {
            return ResourceState(
                Status.LOADING,
                null,
                null
            )
        }
    }
}