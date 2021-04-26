package com.example.thenewyorktime.datasource.local.converter

import androidx.room.TypeConverter
import com.example.thenewyorktime.datasource.local.book.entities.MultimediaLocalModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromJson(json: String?): List<String> {
        if (json.isNullOrEmpty()) {
            return arrayListOf()
        }

        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(listString: List<String>?): String {
        if (listString == null) {
            return "[]"
        }

        return Gson().toJson(listString)
    }

    @TypeConverter
    fun multiMediaFromJson(json: String?): List<MultimediaLocalModel>{
        if(json.isNullOrEmpty()){
            return arrayListOf()
        }
        val type = object : TypeToken<List<MultimediaLocalModel>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun multiMediaToJson(multiMedia: List<MultimediaLocalModel>?): String{
        if(multiMedia == null){
            return ""
        }
        return Gson().toJson(multiMedia)
    }
}