package com.hilt.room.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilt.room.R
import com.hilt.room.db.Student
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentAdapter(var postList : ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.student_list_item , parent  , false))

    override fun getItemCount(): Int  = postList.size


    override fun onBindViewHolder(holder: StudentAdapter.PostViewHolder, position: Int)  =  holder.bind(postList[position])

     fun refreshAdapter(newPostList : List<Student>){
        postList.clear()
        postList.addAll(newPostList)
        notifyDataSetChanged()

    }

    inner class PostViewHolder(view : View) : RecyclerView.ViewHolder(view){

       private val  student_name = view.stu_name
        private  val age = view.stu_age

        fun  bind(model : Student){
            student_name.text = model.fName.toString()
            age.text = model.age.toString()

        }

    }

}