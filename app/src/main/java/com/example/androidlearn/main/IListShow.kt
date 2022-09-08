package com.example.androidlearn.main

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:16 2022/9/8
 */
interface IListShow {
    fun getData(): List<String>

    fun click(position: Int)
}