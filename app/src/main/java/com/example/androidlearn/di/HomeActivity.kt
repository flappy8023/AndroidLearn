package com.example.androidlearn.di

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.MyApp
import dagger.assisted.AssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description: koin依赖注入
 * @Date: Created in 10:14 2023/3/16
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "DI_Test"
    }

    @Inject
    @North
    lateinit var fruit1:IFruit
    @Inject
    @South
    lateinit var fruit2:IFruit
    @Inject
    lateinit var repo: HomeRepo
    @Inject
    lateinit var factory:AssistedTest.TestFactory
    @Inject
    lateinit var bindApple:IFruit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, repo.getNumFromDao().toString())
        Log.d(TAG,"north apple:${fruit1.growth()}")
        Log.d(TAG, "south peach:${fruit2.growth()}")
        val test = factory.createTest("flappy")
        test.printName()

        val entryPoint = EntryPointAccessors.fromApplication<EntryPointTest>(MyApp.myContext)
        entryPoint.getRepo().printA()
        Log.d(TAG, "bind apple:${bindApple.growth()}")
    }
}