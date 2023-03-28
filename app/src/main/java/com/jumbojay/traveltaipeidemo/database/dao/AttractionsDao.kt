package com.jumbojay.traveltaipeidemo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jumbojay.traveltaipeidemo.data.Attractions

@Dao
interface AttractionsDao {
    @get:Query("Select * from attractions")
    val attractionsLiveData: LiveData<List<Attractions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAttractions(attractionsList: List<Attractions>)

    @Query("DELETE FROM attractions")
    fun deleteAll()
}