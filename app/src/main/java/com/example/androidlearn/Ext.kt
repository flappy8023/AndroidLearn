package com.example.androidlearn

import android.content.Context
import android.widget.Toast

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:13 2022/10/14
 */
fun Context.showToast(content:String){
    Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
}