package com.hilt.room.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilt.room.R
import com.hilt.room.db.Student
import com.hilt.room.util.replaceFragment
import com.hilt.room.util.replaceFragmentWithNoHistory
import com.hilt.room.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_student_list.*
@AndroidEntryPoint
class StudentInfoListFragment : Fragment(), LifecycleOwner {

    private var studentInfoListView : View? = null
    var mContainerId:Int = -1
    private var studentAdapter : StudentAdapter? = null
    private val mainViewModel : StudentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentInfoListView = inflater.inflate(R.layout.fragment_student_list, container, false)
        mContainerId = container?.id?:-1
        return  studentInfoListView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.lifecycle.addObserver(mainViewModel)
        add_student_floating_btn.setOnClickListener {
            launchAddStudentFragment()
        }
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        fetchDataFromViewModel()
    }
    fun launchAddStudentFragment(){
        activity?.replaceFragment(StudentInfoFragment(), mContainerId)
    }

    private fun initAdapter(){
        studentAdapter = StudentAdapter(arrayListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = studentAdapter

        }

    }

    private fun fetchDataFromViewModel(){
        // viewModel.fetchRoomData()
        mainViewModel.userFinalList.observe(this,
            Observer<MutableList<Student>> {
                    t -> println("Received UserInfo List $t")
                studentAdapter?.refreshAdapter(t)
            }
        )
    }
}