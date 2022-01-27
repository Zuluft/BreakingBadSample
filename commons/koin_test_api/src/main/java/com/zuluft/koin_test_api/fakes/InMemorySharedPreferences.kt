package com.zuluft.koin_test_api.fakes

import android.content.SharedPreferences

class InMemorySharedPreferences : SharedPreferences, SharedPreferences.Editor {

    private val inMemoryContainer: MutableMap<String, Any> = HashMap()
    private val inMemoryEditor: MutableMap<String, Any> = HashMap()

    override fun getAll(): MutableMap<String, *> {
        return inMemoryContainer
    }

    override fun getString(key: String?, defValue: String?): String {
        return inMemoryContainer[key] as String? ?: defValue ?: ""
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        @Suppress("UNCHECKED_CAST")
        return (inMemoryContainer[key] as MutableSet<String>? ?: defValues)!!
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return inMemoryContainer[key] as Int? ?: defValue
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return inMemoryContainer[key] as Long? ?: defValue
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return inMemoryContainer[key] as Float? ?: defValue
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return inMemoryContainer[key] as Boolean? ?: defValue
    }

    override fun contains(key: String?): Boolean {
        return inMemoryContainer.containsKey(key)
    }

    override fun edit(): SharedPreferences.Editor {
        return this
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {

    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {

    }

    override fun putString(key: String?, value: String?): SharedPreferences.Editor {
        inMemoryEditor[key!!] = value ?: ""
        return this
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
        inMemoryEditor[key!!] = values ?: mutableSetOf<String>()
        return this
    }

    override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
        inMemoryEditor[key!!] = value
        return this
    }

    override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
        inMemoryEditor[key!!] = value
        return this
    }

    override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
        inMemoryEditor[key!!] = value
        return this
    }

    override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
        inMemoryEditor[key!!] = value
        return this
    }

    override fun remove(key: String?): SharedPreferences.Editor {
        inMemoryEditor.remove(key!!)
        return this
    }

    override fun clear(): SharedPreferences.Editor {
        inMemoryEditor.clear()
        return this
    }

    override fun commit(): Boolean {
        inMemoryEditor.forEach { (t, u) ->
            inMemoryContainer[t] = u
        }
        return true
    }

    override fun apply() {
        commit()
    }
}