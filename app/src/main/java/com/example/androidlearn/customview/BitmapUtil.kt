package com.example.androidlearn.customview

import android.content.res.Resources
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import java.io.ByteArrayOutputStream


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

    fun compressQuality(bitmap: Bitmap, format: CompressFormat, quality: Int): Bitmap {
        val outStream = ByteArrayOutputStream()
        bitmap.compress(format, quality, outStream)
        val bytearray = outStream.toByteArray()
        return BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
    }

    /**
     * 采样率压缩
     *
     * @param resource
     * @param id
     * @param inSample
     * @return
     */
    fun compressInSample(resource: Resources, id: Int, inSample: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inSampleSize = inSample
        return BitmapFactory.decodeResource(resource, id, option)

    }

    fun compressMatrix(bitmap: Bitmap, scale: Float): Bitmap {
        val matrix = Matrix()
        matrix.setScale(scale, scale)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun compressFormat(resource: Resources,id: Int):Bitmap{

        return BitmapFactory.decodeResource(resource,id,BitmapFactory.Options().apply { inPreferredConfig = Bitmap.Config.RGB_565 })
    }

    fun getBitmapSize(bitmap: Bitmap, format: CompressFormat): Int {
        val outStream = ByteArrayOutputStream()
        bitmap.compress(format, 100, outStream)
        return outStream.toByteArray().size
    }


}