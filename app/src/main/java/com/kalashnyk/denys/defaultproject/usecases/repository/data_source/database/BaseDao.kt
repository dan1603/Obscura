package com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Inserts a single item with the conflict strategy to replace the item
     *
     * @param item
     * Item to be inserted in the DB
     */
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insert(vararg item: T)

    /**
     * Inserts a list of items with the conflict strategy to replace the items
     *
     * @param items
     * List of items to insert in the DB
     */
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insert(items: List<T>)

    /**
     * Updates the given item in the DB
     *
     * @param item
     */
    @Update
    fun update(item: T)

    @Update
    fun update(items: List<T>)

    /**
     * Deletes the given item from the DB
     *
     * @param item
     */
    @Delete
    fun delete(item: T)
}
