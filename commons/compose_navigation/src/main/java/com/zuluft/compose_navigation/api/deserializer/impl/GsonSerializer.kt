package com.zuluft.compose_navigation.api.deserializer.impl

import com.google.gson.Gson
import com.zuluft.compose_navigation.api.deserializer.Serializer

class GsonSerializer(val gson: Gson) : Serializer<String> {
    override fun serialize(arg: Any): String {
        return gson.toJson(arg)
    }
}