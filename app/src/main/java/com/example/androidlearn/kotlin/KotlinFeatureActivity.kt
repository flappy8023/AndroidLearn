package com.example.androidlearn.kotlin

import com.example.androidlearn.kotlin.coroutine.CoroutineActivity
import com.example.androidlearn.kotlin.flow.FlowTestActivity
import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:51 2022/11/8
 */
class KotlinFeatureActivity : MainActivity() {
    override fun getData() = mutableListOf("Flow", "Coroutine")
    override fun click(position: Int) {
        when (position) {
            0 -> startAct(FlowTestActivity::class.java)
            1 -> startAct(CoroutineActivity::class.java)
        }
    }
}