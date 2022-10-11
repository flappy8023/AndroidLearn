package com.example.androidlearn.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:19 2022/10/11
 */
class FragmentB: FragmentC() {
    init {
        TAG = "FFFFFFFFFFFragmentB"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.fragment_second,container,false)
    }
}