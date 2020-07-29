package com.mayandro.cleanarchbaseproject.data.source.local.room.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter {
    @TypeConverter
    fun convertFamilyToJson(family: Family?): String? {
        family?.let {
            val type: Type = object : TypeToken<Family?>() {}.type
            return Gson().toJson(it, type)
        } ?: kotlin.run { return null }
    }


    @TypeConverter
    fun convertJsonToFamily(json: String?): Family? {
        json?.let {
            val type: Type = object : TypeToken<Family?>() {}.type
            return Gson().fromJson(it, type)
        } ?: kotlin.run { return null }
    }

    @TypeConverter
    fun convertStringListToJson(list: List<String>?): String? {
        list?.let {
            val type: Type = object : TypeToken<List<String>?>() {}.type
            return Gson().toJson(it, type)
        } ?: kotlin.run { return null }
    }


    @TypeConverter
    fun convertJsonToStringList(json: String?): List<String>? {
        json?.let {
            val type: Type = object : TypeToken<List<String>?>() {}.type
            return Gson().fromJson(it, type)
        } ?: kotlin.run { return null }
    }
}