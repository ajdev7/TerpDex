package dev.marcosfarias.terpdex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.marcosfarias.terpdex.database.dao.TerpmonDAO
import dev.marcosfarias.terpdex.model.Terpmon

@Database(entities = [Terpmon::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun terpmonDAO(): TerpmonDAO
}
