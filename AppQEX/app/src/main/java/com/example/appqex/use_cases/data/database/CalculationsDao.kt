package com.example.appqex.use_cases.data.database

import androidx.room.*
import com.example.appqex.use_cases.data.database.Calculations


@Dao
    interface CalculationsDao {
        @Query("SELECT * FROM calculations")
        fun getFromDb():List<Calculations>

        @Insert
        fun insert(calculations: Calculations)
        @Delete
        fun removeAll(calculations: Calculations?)

    }
