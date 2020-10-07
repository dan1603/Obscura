package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessageItemEntity

class Converters {

    @TypeConverter
    fun fromLocationEntity(item: LocationEntity?): String{
        val gson = Gson()
        val type = object : TypeToken<LocationEntity>(){}.type
        return gson.toJson(item, type)
    }

    @TypeConverter
    fun toLocationEntity(ticketTerminalString: String): LocationEntity? {
        val gson = Gson()
        val type = object : TypeToken<LocationEntity>(){}.type
        return gson.fromJson(ticketTerminalString, type)
    }

    @TypeConverter
    fun fromCategoriesList(list: List<CategoryEntity>): String {
        val gson=Gson()
        val type=object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toCategoriesList(apartmentString: String): List<CategoryEntity> {
        val gson=Gson()
        val type=object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.fromJson(apartmentString, type)
    }

    @TypeConverter
    fun fromMessageItemList(value: ArrayList<MessageItemEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<MessageItemEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMessageItemList(value: String): ArrayList<MessageItemEntity> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<MessageItemEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}