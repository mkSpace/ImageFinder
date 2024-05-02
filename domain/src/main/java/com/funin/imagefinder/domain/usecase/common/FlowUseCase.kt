package com.funin.imagefinder.domain.usecase.common

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<I, O> {
    protected abstract fun invoke(params: I): Flow<O>

    fun execute(params: I): Flow<O> = invoke(params)
}