package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.converters.Converters
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.ThemeDAO
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.UserDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.dao.MessagesDao
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessageItemEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.MessagesEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.ThemeEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

@Database(entities = [(UserEntity::class), (ThemeEntity::class), (LocationEntity::class), (CategoryEntity::class), (MessagesEntity::class)], version = 3)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun themeDao(): ThemeDAO
    abstract fun messagesDao(): MessagesDao
}

class DataConverter {

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