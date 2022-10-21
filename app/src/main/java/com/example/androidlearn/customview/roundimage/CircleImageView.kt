package com.example.androidlearn.customview.roundimage

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:08 2022/10/19
 */
class CircleImageView:AppCompatImageView {
    private var clipPath: Path = Path()
    private val DEFAUL_SIZE = 200
    constructor(context: Context):this(context,null)
    constructor(context: Context,attributeSet: AttributeSet?):super(context,attributeSet)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = min(getSize(widthMeasureSpec),getSize(heightMeasureSpec))
        setMeasuredDimension(size,size)
    }

    private fun getSize(measureSpec:Int):Int{
        val mode = MeasureSpec.getMode(measureSpec)
        val specSize=MeasureSpec.getSize(measureSpec)
        if(mode==MeasureSpec.EXACTLY){
            return specSize
        }else if(mode==MeasureSpec.AT_MOST){
            return min(specSize,DEFAUL_SIZE)
        }else{
            return DEFAUL_SIZE
        }
    }
    override fun onDraw(canvas: Canvas?) {
        clipPath.addCircle(width/2f,height/2f,width/2f,Path.Direction.CW)
        canvas?.clipPath(clipPath)
        super.onDraw(canvas)

    }
}