package com.example.androidlearn.jetpack

import com.example.androidlearn.jetpack.lifecycle.LifecycleActivity
import com.example.androidlearn.jetpack.viewmodel.ViewModelActivity
import com.example.androidlearn.jetpack.workmanager.WorkManagerActivity
import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 14:46 2022/11/8
 */
class JetpackActivity : MainActivity() {
    override fun getData() = listOf("LifeCycle", "ViewModel", "LiveData", "WorkManager")
    override fun click(position: Int) {
        when (position) {
            0 -> startAct(LifecycleActivity::class.java)
            1 -> startAct(ViewModelActivity::class.java)
            3 -> startAct(WorkManagerActivity::class.java)
        }
    }
}