package com.example.androidlearn.json

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:42 2022/3/8
 */
abstract class BaseResponse<T> {
    var code = 0
    var msg: String? = null
    var data: T? = null

    val obj: T?
        get() {
            val json = Gson().toJson(data)
            val parameterizedType = javaClass.genericSuperclass as ParameterizedType
            val type = parameterizedType.actualTypeArguments[0]
            data =Gson().fromJson(json,type)
            return data
        }

    inline fun <reified T> getType(t:T):Class<T>{
        return T::class.java
    }
}