package com.example.androidlearn.json

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:19 2022/4/30
 */
class ParameterizedTypeImpl(private val raw: Class<*>) : ParameterizedType {
    override fun getActualTypeArguments(): Array<Type> {
        return arrayOf()
    }

    override fun getRawType(): Type {
        return raw
    }

    override fun getOwnerType(): Type? {
        return null
    }

}