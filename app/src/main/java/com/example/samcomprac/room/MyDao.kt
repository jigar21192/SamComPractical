package com.example.samcomprac.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.samcomprac.model.DataModel

@Dao
interface MyDao {

    @Insert()
    fun insert(model: DataModel)

    @Query("select * from `DataModel`")
    fun getAllData() : List<DataModel>

    @Query("SELECT id FROM DataModel WHERE id = :id LIMIT 1")
    fun getItemId(id: Int): Int?


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: DataModel): Int
}