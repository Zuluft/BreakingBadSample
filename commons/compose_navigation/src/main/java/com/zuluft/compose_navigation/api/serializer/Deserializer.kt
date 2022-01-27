package com.zuluft.compose_navigation.api.serializer

interface Deserializer<T> {
    fun <P> deserialize(arg: T, clazz:Class<P>): P
}