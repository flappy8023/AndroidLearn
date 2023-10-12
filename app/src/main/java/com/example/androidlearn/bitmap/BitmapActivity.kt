package com.example.androidlearn.bitmap

import com.example.androidlearn.main.MainActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:23 2022/11/8
 */
class BitmapActivity:MainActivity() {
    override fun getData() = mapOf("Bitmap压缩" to BitmapCompressActivity::class.java)
}