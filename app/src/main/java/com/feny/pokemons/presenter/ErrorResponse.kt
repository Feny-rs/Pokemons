package com.feny.pokemons.presenter

class ErrorResponse(val code: Int, message: String) : Throwable("($code) $message")
