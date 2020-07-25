package com.hilt.room.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hilt.room.db.Student
import com.hilt.room.repository.RoomDBRepository
import kotlinx.coroutines.launch

class StudentViewModel @ViewModelInject constructor(private val roomDBRepository: RoomDBRepository) :
    ViewModel(),LifecycleObserver {

    private  val insertedId =  MutableLiveData<Long>()
    private val  error = MutableLiveData<String>()
    var userFinalList: LiveData<MutableList<Student>> = MutableLiveData<MutableList<Student>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchStudentData(){
        viewModelScope.launch {

            userFinalList = roomDBRepository.fetchStudents()
        }
    }

     fun insertStudentInfo(student: Student) {
         viewModelScope.launch {
            if(student.fName.isNullOrEmpty() ||
                    student.lname.isNullOrEmpty() ||
                    student.standard.isNullOrEmpty() ||
                    student.age.toString().isNullOrEmpty()){
                error.postValue( "Input Fields cannot be Empty")

            }else{
                val userId: Long = roomDBRepository.insertStudentData(student)
                insertedId.postValue(userId)
            }
         }
     }

    fun fetchError(): LiveData<String> = error

    fun fetchInsertedId():LiveData<Long> = insertedId

}