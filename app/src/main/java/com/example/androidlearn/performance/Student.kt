package com.example.androidlearn.performance

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:20 2023/3/14
 */
class Student(var name: String, val age: Int, val gender: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Student) return false
        return other.age == age && other.name == name && other.gender == gender
    }
}