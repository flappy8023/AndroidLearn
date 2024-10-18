package com.example.androidlearn.kotlin.flow

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityFlowTestBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class FlowTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "FlowTestActivity"
    }

    private val viewModel by viewModels<FlowTestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory()).get(
            FlowTestViewModel::class.java
        );
        val binding = DataBindingUtil.setContentView<ActivityFlowTestBinding>(
            this,
            R.layout.activity_flow_test
        )
//        testFlow()
//        testFlowException()
//        testToList()
//        testSingle()
        testTransform()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun testTransform() {
        lifecycleScope.launch {
            flowOf(1, 2, 3).transform<Int, String> {
                emit("hello $it")
            }.collect {
                Log.d(TAG, "testTransform: $it")
            }
            val debounceList = flow {
                emit(10)
                emit(20)
                emit(1)
                emit(2)
            }.debounce { 200 }.toList()
            Log.d(TAG, "testTransform: debounceList = $debounceList")
            val distinctList =
                flowOf(11, 11, 12, 12, 13, 14, 15, 16, 15, 15, 17).distinctUntilChanged().toList()
            Log.d(TAG, "testTransform: distinctUntilChanged = $distinctList")
            val flatmapList = flowOf(1, 2, 3, 4, 45).flatMapLatest {
                flowOf("hello flatmap $it")

            }.toList()
            Log.d(TAG, "testTransform: flatmapList = $flatmapList")
            val bufferTime = measureTimeMillis {
                flow {
                    (1..5).forEach {
                        delay(200)
                        emit(it)
                    }
                }.conflate().collect {
                    delay(500)
                    Log.d(TAG, "testTransform: $it")
                }
            }
            Log.d(TAG, "testTransform: buffer operator time = $bufferTime")
            flowOf(12, 3, 4).zip(flowOf("1", "2")) { a, b ->
                "${a * a} ${b.repeat(2)}"

            }.collect {
                Log.d(TAG, "zip result: $it")
            }

            val flow1 = MutableStateFlow<Int>(1)
            val flow2 = MutableStateFlow<String>("jike")
            val flow3 = MutableStateFlow<Int>(0)

            launch {
                combine(flow1, flow2, flow3) { a, b, c ->
                    return@combine "$a use $c beated $b"
                }.collect {
                    Log.d(TAG, "combine: $it")
                }
                Log.d(TAG, "after combine: ")
            }
            withContext(Dispatchers.IO) {
                delay(100)
                flow1.emit(20)
            }
            withContext(Dispatchers.IO) {
                delay(200)
                flow2.emit("sword")
            }
            withContext(Dispatchers.IO) {
                delay(100)
                flow3.emit(232)
            }
            flow {
                emit(flowOf(1, 2, 3))
                emit(flowOf(23, 323, 4523))
            }.flattenConcat().collect {
                Log.d(TAG, "flattenConcat = $it")
            }
            flow {
                emit(1111)
                Log.d(TAG, "testTransform emit: ${Thread.currentThread().name}")
            }.flowOn(Dispatchers.Main).onEach {
                Log.d(TAG, "testTransform onEach:${Thread.currentThread().name} ")
            }.launchIn(CoroutineScope(Dispatchers.IO))


        }
    }

    private fun testSingle() {
        lifecycleScope.launch {
            //FIXME 会抛出java.lang.IllegalArgumentException: Flow has more than one element
//            val value = flowOf(1, 2, 3, 4, 5).single()
//            Log.d("ffffff", "testSingle: $value")
            val single = flowOf(1).single()
            Log.d("ffff", "testSingle: single = $single")
        }
    }

    private fun testToList() {
        lifecycleScope.launch {
            val list = (0..10).asFlow().toList()
            Log.d("ffff", "toList = $list")
        }
        //看来toList是需要数据全部发送完才收集
//        lifecycleScope.launch {
//            val list = flow<Int> {
//                while (true)
//                    emit(1)
//            }.toList()
//            Log.d(TAG, "testToList: list = $list")
//        }
    }

    private fun testFlowException() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                "fffffff",
                "got exception:${throwable.message}"
            )
        }
        lifecycleScope.launch(exceptionHandler) {
            Log.e("Ffff", "i will throw an exception")
            launch {
                throw NullPointerException("sub coroutine throw NPE")
            }
            delay(100)
            throw java.lang.NullPointerException("hello NPE")
        }
    }

    private fun testFlow() {
        lifecycleScope.launch {
            //冷流不同的消费者互不影响，仅在collect时才开始生产数据
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: $it")
            }

        }
        lifecycleScope.launch {
            delay(3000)
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: --->$it")
            }
        }
        lifecycleScope.launch {
            val time = measureTimeMillis {
                val deferred1 = async { fetch1() }
                val deferred2 = async { fetch2() }
                val list = listOf(deferred1, deferred2).awaitAll()
                //                Log.d(TAG, "onCreate: $list ")
            }
            Log.d(TAG, "onCreate: time = $time")
        }
    }

    private suspend fun fetch1(): String {
        Log.d(TAG, "fetch1: begin")
        delay(2000)
        return "fetch1 done"
    }

    private suspend fun fetch2(): String {
        Log.d(TAG, "fetch2: begin")
        delay(5000)
        return "fetch2 done"
    }
}