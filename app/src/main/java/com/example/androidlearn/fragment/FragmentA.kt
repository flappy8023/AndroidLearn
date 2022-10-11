package com.example.androidlearn.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:15 2022/10/11
 */
class FragmentA : FragmentC() {
    init {
        TAG = "FFFFFFFFFFFragmentA"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
}