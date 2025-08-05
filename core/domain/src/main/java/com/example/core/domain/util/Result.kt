package com.example.core.domain.util

import com.example.core.domain.util.map

sealed interface Result<out D, out E: Error> {

    data class Success<out D>(val data: D): Result<D, Nothing>

    data class Error<out E: com.example.core.domain.util.Error>(val error: E): Result<Nothing, E>
}

//This lets you transform the success value while preserving the error.
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this){
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}


//This will use the map to return unity my default
//Used when you don’t care about the data value — you're just interested in whether it succeeded or failed.
//It uses Unit (Void in Swift) to represent “no data.”
//Useful in commands like "save this" where you don’t return data
fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyDataResult<E>{
    return map { }
}

//Just makes the function signature cleaner: you return Result<Unit, Error> when you don’t have data to return.
typealias EmptyDataResult<E> = Result<Unit, E>
