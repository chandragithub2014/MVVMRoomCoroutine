package com.hilt.room.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hilt.room.R
import com.hilt.room.db.Student
import com.hilt.room.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_student_info.*
@AndroidEntryPoint
class StudentInfoFragment : Fragment() {

    private val mainViewModel : StudentViewModel by viewModels()
    private var studentInfoView : View? = null
    var mContainerId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentInfoView = inflater.inflate(R.layout.fragment_student_info, container, false)
        mContainerId = container?.id?:-1
        return  studentInfoView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_save.setOnClickListener {
            val student = getEnteredStudentDetails()
            mainViewModel.insertStudentInfo(student)
        }
        button_cancel.setOnClickListener {
            activity?.let{

                activity?.supportFragmentManager?.popBackStack()
            }
        }
        observeViewModel()

    }


    fun getEnteredStudentDetails() : Student {
        var age : Int = 0
        if(!ed_age.text.toString().isNullOrEmpty() && ed_age.text.toString().isDigitsOnly()) {
            age =  Integer.parseInt(ed_age.text.toString())
        }

       return Student(0L,ed_student_name.text.toString(),
                       ed_last_name.text.toString(),
          ed_standard.text.toString(), age)
    }


    fun observeViewModel(){
        mainViewModel.fetchError().observe(viewLifecycleOwner,
            Observer<String> { t -> Toast.makeText(activity,t, Toast.LENGTH_LONG).show() })

        mainViewModel.fetchInsertedId().observe(viewLifecycleOwner,
            Observer<Long> { t ->
                if(t != -1L){



                    Toast.makeText(activity,"Inserted Successfully in DB $t", Toast.LENGTH_LONG).show()
                    activity?.let{

                        activity?.supportFragmentManager?.popBackStack()
                    }

                }else{
                    Toast.makeText(activity,"Insert Failed",Toast.LENGTH_LONG).show()

                }

            })
    }

}