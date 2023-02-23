package com.example.androidlearn.classloader

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:16 2023/2/22
 */
class MyClassLoader(val path: String) : ClassLoader() {
    override fun loadClass(name: String?): Class<*> {
        val file = File(path, name + ".class")
        var input: InputStream? = null
        var output: ByteArrayOutputStream? = null
        try {
            input = FileInputStream(file)
            output = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length = input.read(buffer)
            while (length != -1) {
                output.write(buffer, 0, length)
                length = input.read(buffer)
            }
            val bytes = output.toByteArray()
            return defineClass(name, bytes, 0, bytes.size)
        } catch (e: Exception) {

        } finally {
            try {
                input?.close()
            } catch (e: Exception) {
            }
            try {
                output?.close()
            } catch (e: Exception) {
            }
        }

        return super.loadClass(name)
    }
}

fun main() {
    val myLoader =
        MyClassLoader("C:\\projects\\AndroidLearn\\app\\src\\main\\java\\com\\example\\androidlearn\\classloader")
    val clz = myLoader.loadClass("com.example.androidlearn.classloader.StudentA")
    val obj = clz.newInstance()
    println("obj classLoader = ${obj.javaClass.classLoader}")
    val callMethod = clz.getDeclaredMethod("callMom")
    callMethod.invoke(obj)
}