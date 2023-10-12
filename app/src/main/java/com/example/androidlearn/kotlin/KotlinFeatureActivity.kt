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
    override fun getData() =
        mapOf("Flow" to FlowTestActivity::class.java, "Coroutine" to CoroutineActivity::class.java)
}