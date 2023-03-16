package com.example.androidlearn.performance

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:16 2023/3/14
 */
class DiffAdapter(var oldList: List<Student>, var newList: List<Student>) :
    RecyclerView.Adapter<DiffAdapter.MyHolder>() {
    companion object {
        private const val TAG = "DiffAdapter"
    }

    class MyHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_rv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: position = $position")
        val student = newList[position]
        holder.title.text = "name:${student.name}, age:${student.age}, gender:${student.gender}"
    }

    fun updateList(list: List<Student>) {
        val callback = MyDiffCallback(newList, list)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        oldList = newList
        newList = list
    }

    class MyDiffCallback(val oldList: List<Student>, val newList: List<Student>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].equals(newList[newItemPosition])
        }
    }
}