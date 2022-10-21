package com.example.androidlearn.customview

import android.graphics.*


/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:50 2022/10/19
 */
object BitmapUtil {
    fun roundBitmapByShader(bitmap: Bitmap, outWidth: Int, outHeight: Int, radius: Int): Bitmap {
        val originWidth = bitmap.width
        val originHeight = bitmap.height
        val scaleW = outWidth * 1f / originWidth
        val scaleH = outHeight * 1f / originHeight
        //
        val matrix = Matrix().apply {
            setScale(scaleW, scaleH)
        }
        val bitmapShader =
            BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP).apply {
                setLocalMatrix(matrix)
            }
        //定义目标bitmap
        val outBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val paint = Paint().apply {
            isAntiAlias = true
            shader = bitmapShader
        }
        val outCanvas = Canvas(outBitmap)
        outCanvas.drawRoundRect(
            0f,
            0f,
            outWidth.toFloat(),
            outHeight.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            paint
        )
        return outBitmap
    }

    fun roundBitmapByXfermode(bitmap: Bitmap, outWidth: Int, outHeight: Int, radius: Int): Bitmap {
        val newBt = scaleBitmap(bitmap, outWidth, outHeight)
        val targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(targetBitmap)
        canvas.drawARGB(0, 0, 0, 0)
        val paint = Paint()
        paint.isAntiAlias = true
        val rectF = RectF(0f, 0f, outWidth.toFloat(), outHeight.toFloat())
        canvas.drawRoundRect(
            rectF,
            radius.toFloat(),
            radius.toFloat(),
            paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        val rect = Rect(0, 0, outWidth, outHeight)
        canvas.drawBitmap(newBt, rect, rect, paint)
        return targetBitmap
    }

    fun scaleBitmap(bitmap: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val scaleW = outWidth * 1f / bitmap.width
        val scaleH = outHeight * 1f / bitmap.height
        val matrix = Matrix().apply {
            setScale(scaleW, scaleH)
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

}