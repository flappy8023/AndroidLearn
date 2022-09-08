package com.example.androidlearn.animator

import com.example.androidlearn.main.MainActivity

class AnimatorActivity : MainActivity() {
    override fun getData(): List<String> {
        return listOf("插值器")
    }

    override fun click(position: Int) {
        when (position) {
            0 -> startAct(EvaluatorActivity::class.java)
        }
    }
}