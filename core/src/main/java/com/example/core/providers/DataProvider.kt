package com.example.core.providers

interface DataProvider<T> {

    fun requestData(callback: (item: T) -> Unit)
}