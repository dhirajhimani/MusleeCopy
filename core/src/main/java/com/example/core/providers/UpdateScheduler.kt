package com.example.core.providers

interface UpdateScheduler<T> {
    fun scheduleUpdate(items: List<T>)
}
