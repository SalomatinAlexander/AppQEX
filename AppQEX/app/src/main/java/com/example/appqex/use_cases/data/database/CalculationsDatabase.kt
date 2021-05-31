package com.example.appqex.use_cases.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appqex.use_cases.data.database.Calculations
import com.example.appqex.use_cases.data.database.CalculationsDao


@Database(entities = [Calculations::class], version = 1, exportSchema = false)
abstract class CalculationsDatabase : RoomDatabase() {
    abstract fun calculationsDao(): CalculationsDao?
}