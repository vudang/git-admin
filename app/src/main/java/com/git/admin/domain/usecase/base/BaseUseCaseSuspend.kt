package com.git.admin.domain.usecase.base

abstract class BaseUseCaseSuspend<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}