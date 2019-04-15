package com.example.core.providers

interface DataMapper<S, R> {
    fun map(source: S): R
}
