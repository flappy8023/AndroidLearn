package com.example.androidlearn.multithread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val t1 = Thread {
            for (i in 0..100) {
                Thread.sleep(10)
                binding.progressbar1.progress = i
            }
        }
        val t2 = Thread {
            for (i in 0..100) {
                Thread.sleep(10)
                binding.progressbar2.progress = i
            }
        }
        binding.btStart.setOnClickListener {

            t1.start()
            t2.start()
        }
        binding.btJoin.setOnClickListener {

            Thread {
                t1.start()
                //阻塞当前线程，等t1执行完在启动t2
                t1.join()
                t2.start()
            }.start()
        }
        binding.btYeild.setOnClickListener {

            val t1 = Thread {
                for (i in 0..100) {
//                    Thread.sleep(10)
                    if (i == 50) {
                        Thread.yield()
                    }
                    binding.progressbar1.postDelayed({
                        binding.progressbar1.progress = i
                    }, 10)
                }
            }
            val t2 = Thread {
                for (i in 0..100) {
//                    Thread.sleep(10)
                    binding.progressbar2.postDelayed({ binding.progressbar2.progress = i }, 10)
                }
            }
            t1.start()
            t2.start()
        }
    }
}