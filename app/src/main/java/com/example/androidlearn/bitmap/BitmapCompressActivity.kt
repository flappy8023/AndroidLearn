package com.example.androidlearn.bitmap

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidlearn.R
import com.example.androidlearn.customview.BitmapUtil
import com.example.androidlearn.databinding.ActivityBitmapCompressBinding

class BitmapCompressActivity : AppCompatActivity() {
    companion object {
        private val COMPRESS_QUALITY = 0
        private val COMPRESS_INSAMPLE = 1
        private val COMPRESS_MATRIX = 2
        private val COMPRESS_ENCODE = 3

        private val IMG_TYPE_JPEG = 0
        private val IMG_TYPE_PNG = 1
    }

    private var compressType = COMPRESS_QUALITY
    private var imgType = IMG_TYPE_JPEG
    private var curProgress = 100
    lateinit var binding: ActivityBitmapCompressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bitmap_compress)
        initListener()

        doCompress()
    }

    private fun initListener() {
        binding.rgType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_quality -> {
                    compressType = COMPRESS_QUALITY
                    binding.seekbar.visibility = View.VISIBLE

                    binding.seekbar.max = 100
                }
                R.id.rb_insample -> {
                    compressType = COMPRESS_INSAMPLE
                    binding.seekbar.visibility = View.VISIBLE
                    binding.seekbar.max = 4
                }
                R.id.rb_matrix -> {
                    compressType = COMPRESS_MATRIX
                    binding.seekbar.max = 4
                    binding.seekbar.visibility = View.VISIBLE
                }
                R.id.rb_configFormat -> {
                    compressType = COMPRESS_ENCODE
                    binding.seekbar.max = 100
                    binding.seekbar.visibility = View.GONE
                }
            }
        }
        binding.rgImgType.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_jpg) {
                imgType = IMG_TYPE_JPEG
            } else {
                imgType = IMG_TYPE_PNG
            }
        }
        binding.seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    curProgress = progress
                    doCompress()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun doCompress() {
        val srcBitmap = if (imgType == IMG_TYPE_JPEG) BitmapFactory.decodeResource(
            resources,
            R.drawable.img_jpg
        ) else BitmapFactory.decodeResource(resources, R.drawable.img_png)
        val format = if (imgType == IMG_TYPE_JPEG) CompressFormat.JPEG else CompressFormat.PNG
        val imgId = if (imgType == IMG_TYPE_JPEG) R.drawable.img_jpg else R.drawable.img_png
        var bitmap: Bitmap? = null
        when (compressType) {
            COMPRESS_QUALITY -> {
                bitmap = BitmapUtil.compressQuality(srcBitmap, format, curProgress)
                binding.ivImg.setImageBitmap(bitmap)
            }
            COMPRESS_INSAMPLE -> {
                bitmap = BitmapUtil.compressInSample(resources, imgId, curProgress)
                binding.ivImg.setImageBitmap(bitmap)
            }
            COMPRESS_MATRIX -> {
                bitmap = BitmapUtil.compressMatrix(srcBitmap, 1f / curProgress)
                binding.ivImg.setImageBitmap(bitmap)
            }
        }
        binding.ivImg.setImageBitmap(bitmap)
        bitmap?.let {
            showSize(it, if (imgType == IMG_TYPE_PNG) CompressFormat.PNG else CompressFormat.JPEG)
        }
    }

    private fun showSize(bitmap: Bitmap, format: CompressFormat) {
        val memorySize = bitmap.byteCount / 1024
        val fileSize = BitmapUtil.getBitmapSize(bitmap, format)
        binding.tvInfo.text =
            "memorySize:${memorySize}KB,fileSize:${fileSize / 1024}kb,width:${bitmap.width},height:${bitmap.width}"
    }

}