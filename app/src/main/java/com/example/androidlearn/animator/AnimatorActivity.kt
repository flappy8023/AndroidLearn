package com.example.androidlearn.animator

import com.example.androidlearn.main.MainActivity

class AnimatorActivity : MainActivity() {
    override fun getData(): Map<String, Class<*>> {
        return mapOf(
            "插值器" to EvaluatorActivity::class.java,
            "AnimatorSet" to AnimatorSetAct::class.java,
            "MotionLayout" to MotionAct::class.java
        )
    }

}