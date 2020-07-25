package com.hilt.room.repository

import com.hilt.room.db.Student
import com.hilt.room.db.StudentDao
import javax.inject.Inject

class RoomDBRepository @Inject  constructor(private val studentDao: StudentDao){
    suspend fun  insertStudentData(student: Student) = studentDao.insert(student)

    suspend fun  fetchStudents() = studentDao.fetch()
}