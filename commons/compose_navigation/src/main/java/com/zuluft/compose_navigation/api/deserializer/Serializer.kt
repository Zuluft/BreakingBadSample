package com.zuluft.compose_navigation.api.deserializer

interface Serializer<T> {
    fun serialize(arg: Any): T
}