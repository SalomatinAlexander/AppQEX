package com.example.appqex.use_cases.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calculations(
    val calculation:String? = " ")
     {
         @PrimaryKey(autoGenerate = true)
         var Id:Int = 0

}