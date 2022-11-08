package com.example.androidlearn.bitmap

import android.graphics.Bitmap
import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:23 2022/11/8
 */
class BitmapActivity:MainActivity() {
    override fun getData() = listOf("Bitmap压缩")
    override fun click(position: Int) {
        when(position){
            0->startAct(BitmapCompressActivity::class.java)
        }
    }
}