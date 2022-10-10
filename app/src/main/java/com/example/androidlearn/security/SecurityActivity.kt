package com.example.androidlearn.security

import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:34 2022/9/23
 */
class SecurityActivity :MainActivity(){
    override fun getData(): List<String> {
        return listOf("DH密钥交换")
    }

    override fun click(position: Int) {
        when(position){
            0-> startAct(DHActivity::class.java)
        }
    }
}