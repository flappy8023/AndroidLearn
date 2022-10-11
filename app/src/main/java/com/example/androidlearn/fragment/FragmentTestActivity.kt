package com.example.androidlearn.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.databinding.ActivityFragmentTestBinding

class FragmentTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragmentTestBinding
    val fragmentA = FragmentA()
    val fragmentB = FragmentB()
    val fragmentC = FragmentC()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addABC.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.container.id, fragmentA, "a")
                .add(binding.container.id, fragmentB, "b")
                .add(binding.container.id, fragmentC, "c")
                .addToBackStack(null)
                .commit()
        }
        binding.removeBC.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(fragmentB)
                .remove(fragmentC)
                .addToBackStack(null)
                .commit()
        }
        binding.hideBC.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .hide(fragmentB)
                .hide(fragmentC)
                .addToBackStack(null)
                .commit()
        }

    }
}