package com.example.androidlearn.performance

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:19 2023/3/14
 */
class RecyclerActivity : AppCompatActivity(R.layout.activity_recyclerview) {
    private val list: MutableList<Student> by lazy {
        mutableListOf(
            Student("xiaowang", 11, 1),
            Student("xiaoli", 12, 1),
            Student("xiaolu", 11, 0)
        )
    }
    private lateinit var adapter: DiffAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        adapter = DiffAdapter(listOf(), list)
        recyclerView.adapter = adapter
        findViewById<Button>(R.id.bt_refresh1).setOnClickListener {
            adapter.updateList(
                mutableListOf(
                    Student("xiaowang11", 11, 1),
                    Student("xiaoli11", 12, 1),
                    Student("xiaolu11", 11, 0)
                )
            )
        }

        findViewById<Button>(R.id.bt_refresh2).setOnClickListener {
            list.removeAt(0)
            adapter.updateList(list)
        }
    }

}