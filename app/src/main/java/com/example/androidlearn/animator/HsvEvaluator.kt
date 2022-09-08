package com.example.androidlearn.animator

import android.animation.TypeEvaluator
import android.graphics.Color

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:34 2022/9/6
 */
class HsvEvaluator : TypeEvaluator<Int> {
    val hsvStart = FloatArray(3) { 0f }
    val hsvEnd = FloatArray(3) { 0f }
    val hsvOut = FloatArray(3) { 0f }

    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        //1.分别将开始、结束颜色转化为HSV格式
        val start = Color.colorToHSV(startValue, hsvStart)
        val end = Color.colorToHSV(endValue, hsvEnd)
        //2.根据百分比分别计算各个通道的值
        hsvOut[0] = hsvStart[0] + (hsvEnd[0] - hsvStart[0]) * fraction
        hsvOut[1] = hsvStart[1] + (hsvEnd[1] - hsvStart[1]) * fraction
        hsvOut[2] = hsvStart[2] + (hsvEnd[2] - hsvStart[2]) * fraction
        val alpha =
            (startValue.shr(24) + (endValue.shr(24) - startValue.shr(24)) * fraction).toInt()
        //3.再次转化为argb格式返回
        return Color.HSVToColor(alpha, hsvOut)
    }
}