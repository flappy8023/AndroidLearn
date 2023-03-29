package com.example.androidlearn.performance

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:20 2023/3/14
 */
class Student(var id: Int, var name: String?, val age: Int, val gender: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Student) return false
        return other.age == age && other.name == name && other.gender == gender
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}