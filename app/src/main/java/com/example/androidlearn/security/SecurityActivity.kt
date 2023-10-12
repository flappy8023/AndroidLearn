package com.example.androidlearn.security

import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:34 2022/9/23
 */
class SecurityActivity :MainActivity(){
    override fun getData() =
        mapOf("DH密钥交换" to DHActivity::class.java)


}