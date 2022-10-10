package com.example.androidlearn.security

import android.util.Base64
import android.util.Log
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityDhBinding
import com.flappy.wanandroid.base.BaseActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:36 2022/9/23
 */
class DHActivity : BaseActivity<ActivityDhBinding>() {
    companion object {
        private const val TAG = "DHActivity"
    }

    override fun initView() {
        binding.btP.setOnClickListener {
            val p = Base64.encodeToString(DH.get().p, 0)
            Log.d(TAG, "origin p:$p")
        }

        binding.btG.setOnClickListener {
            val g = Base64.encodeToString(DH.get().g,0)
            Log.d(TAG,"origin g:$g")
        }
        binding.btPkey.setOnClickListener {
            val key = Base64.encodeToString(DH.get().pubKey,0)
            Log.d(TAG,"origin pubKey:$key")
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_dh
}