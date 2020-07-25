package com.hilt.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hilt.room.util.replaceFragmentWithNoHistory
import com.hilt.room.view.StudentInfoFragment
import com.hilt.room.view.StudentInfoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragmentWithNoHistory(StudentInfoListFragment(), R.id.container_fragment)
    }
}


//Student Details
//Table Name : Student
//Cols:  First Name, Last Name ,class,Age