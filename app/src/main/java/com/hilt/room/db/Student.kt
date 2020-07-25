package com.hilt.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student (
    @PrimaryKey(autoGenerate = true)
    val studentId : Long = 0L,
    val fName : String,
    val lname : String,
    val standard : String,
    val age : Int

)