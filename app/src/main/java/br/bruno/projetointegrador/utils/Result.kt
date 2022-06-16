package br.bruno.projetointegrador.utils

sealed class Result<T>

data class Success<T>(val data: T) : Result<T>()

data class Error<T>(val msg: String = "erro") : Result<T>()

class Loading<T> : Result<T>()