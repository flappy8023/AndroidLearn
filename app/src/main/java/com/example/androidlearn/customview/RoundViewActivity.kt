package com.example.androidlearn.customview

import android.graphics.BitmapFactory
import android.graphics.Outline
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidlearn.R

class RoundViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_view)
        val iv2 = findViewById<ImageView>(R.id.iv_2)
        //2.ViewOutLineProvider
        iv2.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                view?.let {
                    outline?.setRoundRect(0, 0, it.width, it.height, it.width / 2f)
                }
            }
        }
        iv2.clipToOutline = true

        //4.BitmapShader
        val iv4 = findViewById<ImageView>(R.id.iv_4)
        iv4.post {
            val bitmap = BitmapUtil.roundBitmapByShader(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.test
                ), iv4.width, iv4.height, iv4.width / 2
            )
            iv4.setImageBitmap(bitmap)
        }
        //5.Glide
        val iv5: ImageView = findViewById(R.id.iv_5)
        val requestOptions =
            RequestOptions().transform(RoundedCorners(resources.displayMetrics.densityDpi / 160 * 50))

        Glide.with(this).load(R.drawable.test).apply(requestOptions).into(iv5)

        //6.Xfermode
        val iv6: ImageView = findViewById(R.id.iv_6)
        iv6.post {
            val bitmap = BitmapUtil.roundBitmapByXfermode(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.test
                ), iv6.width, iv6.height, iv6.width / 2
            )
            iv6.setImageBitmap(bitmap)
        }
        //7.RoundedBitmapDrawable
        val iv7 = findViewById<ImageView>(R.id.iv_7)
        iv7.post {
            val bitmap = BitmapUtil.scaleBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.test),
                iv7.width,
                iv7.height
            )
            val roundedBitmap = RoundedBitmapDrawableFactory.create(resources,bitmap)
            //内部也是BitmapShader
            roundedBitmap.cornerRadius = iv7.width/2f
            roundedBitmap.setAntiAlias(true)
            iv7.setImageDrawable(roundedBitmap)
        }
    }
}