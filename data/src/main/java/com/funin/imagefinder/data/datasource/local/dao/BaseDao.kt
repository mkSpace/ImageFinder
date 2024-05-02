package com.funin.imagefinder.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class BaseDao<T : Any> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg objects: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(objects: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(`object`: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOrIgnore(vararg objects: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOrIgnore(objects: List<T>): List<Long>

    @Update
    abstract fun update(vararg objects: T)

    @Update
    abstract fun update(objects: List<T>): Int

    @Delete
    abstract fun delete(vararg objects: T)

    @Delete
    abstract fun delete(objects: List<T>)

    @Transaction
    open fun insertOrUpdate(vararg objects: T) {
        insertOrIgnore(*objects)
            .zip(objects)
            .mapNotNull { (result: Long, obj: T) -> obj.takeIf { result == -1L } }
            .also { update(it) }
    }

    @Transaction
    open fun insertOrUpdate(objects: List<T>) {
        insertOrIgnore(objects)
            .zip(objects)
            .mapNotNull { (result: Long, obj: T) -> obj.takeIf { result == -1L } }
            .also { update(it) }
    }
}