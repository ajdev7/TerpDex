package dev.marcosfarias.terpdex.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.terpdex.model.Terpmon

@Dao
interface TerpmonDAO {

    @Query("SELECT * FROM terpmon WHERE id = :id")
    fun getById(id: String?): LiveData<Terpmon>

    @Query("SELECT * FROM terpmon WHERE id IN(:evolutionIds)")
    fun getEvolutionsByIds(evolutionIds: List<String>): LiveData<List<Terpmon>>

    @Query("SELECT * FROM terpmon")
    fun all(): LiveData<List<Terpmon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(terpmon: List<Terpmon>)

    @Query("DELETE FROM terpmon")
    fun deleteAll()

    @Delete
    fun delete(model: Terpmon)
}
