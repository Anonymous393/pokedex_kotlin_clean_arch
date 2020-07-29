package com.mayandro.cleanarchbaseproject.data.model

sealed class UseCaseResult <out F, out S> {
    data class Failure<out F>(val failure: F): UseCaseResult<F, Nothing>()
    data class Success<out S>(val success: S): UseCaseResult<Nothing, S>()
}