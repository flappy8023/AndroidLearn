package com.example.androidlearn.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.databinding.ActAnimatorSetBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:59 2023/2/23
 */
class AnimatorSetAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActAnimatorSetBinding = ActAnimatorSetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val scale = ObjectAnimator.ofFloat(binding.tv1, "scaleX", 1.0f, 2.0f).setDuration(500)
        val rotate = ObjectAnimator.ofFloat(binding.tv1, "rotationX", 0.0f, 100f).setDuration(500)
        val alpha = ObjectAnimator.ofFloat(binding.tv2, "alpha", 0f, 1f, 0.5f).setDuration(1000)
        val translation =
            ObjectAnimator.ofFloat(binding.tv2, "translationX", 0f, 100f, 200f).setDuration(1000)
        val set =
            AnimatorSet().apply { play(scale).after(rotate).before(alpha).before(translation) }
        binding.apply {
            btStart.setOnClickListener {
                set.start()
            }
            btStop.setOnClickListener {
//                set.cancel()
                propertyValueHolderTest(tv2)
            }
        }
        //valueAnimator
        val valueAnimator = ValueAnimator.ofFloat(0f, 100f)
        valueAnimator.addUpdateListener {
            Log.d("fff", "value = ${it.animatedValue}")
        }
        valueAnimator.start()

    }

    private fun propertyValueHolderTest(view: View) {
        //PropertyValueHOlder
        val holder1 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f)
        val holder2 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.5f, 1.0f)
        ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2).setDuration(1000).start()
    }
}