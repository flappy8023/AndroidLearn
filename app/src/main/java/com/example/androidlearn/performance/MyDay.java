package com.example.androidlearn.performance;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author: luweiming
 * @Description:性能优化：使用intDef、StringDef替换枚举类型
 * @Date: Created in 16:42 2023/3/14
 */
@IntDef({Const.MONDAY, Const.TUESDAY, Const.WEDNESDAY, Const.THURSDAY, Const.FRIDAY, Const.SATURDAY, Const.SUNDAY})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@interface MyDay {
}
