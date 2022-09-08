package com.example.androidlearn.main

import com.example.androidlearn.R
import com.example.androidlearn.databinding.ItemMainRvBinding
import xyz.flappy.core.BaseRecyclerViewAdapter

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:10 2022/9/8
 */
class MainAdapter:BaseRecyclerViewAdapter<String,ItemMainRvBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.item_main_rv
    }

    override fun bindView(binding: ItemMainRvBinding, data: String, holder: Holder) {
        binding.tvTitle.text = data
    }
}