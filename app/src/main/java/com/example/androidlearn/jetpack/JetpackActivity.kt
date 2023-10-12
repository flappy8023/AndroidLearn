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
    override fun getData() = mapOf(
        "LifeCycle" to LifecycleActivity::class.java,
        "ViewModel" to ViewModelActivity::class.java,
        "WorkManager" to WorkManagerActivity::class.java
    )

}