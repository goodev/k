@file:JvmName("Utils")
package org.goodev.kotlindemo

import java.util.*


/**
 * 一、
 * @file:JvmName() 来指定生成的 Java 类的名称。 通过 Kotlin Bytecode 可以看到最终生成的类名。
 *
 * 二、 lateinit 修饰符， 对于非 null 的变量定义可以稍后再初始化其值，
 *         lazy 延迟初始化
  * MainActivity#onCreate
 *
 *
 */

fun a() {

}


/**
 * 三、 扩展函数 让代码看起来更简洁
 * 比如 Java 中定义这么一个函数：
 *
// DateUtils.class
static boolean isTuesday(Date date) {
   return date.getDay() == 2;
}

// 需要这么使用
boolean tuesday = DateUtils.isTuesday(date);

 */

// 需要注意， isTuesday 是扩展了 Date 这个类的，所以在函数体内可以直接使用这个类中的可见的函数和属性
// 比如下面的 getDay 函数可以直接使用， 看下生成的对应的 java 代码
fun Date.isTuesday(): Boolean {
    return getDay() == 2
}

fun testExtFun() {
    val date = Date()
    val tuesday = date.isTuesday();
}

// 只有一行代码的函数可以简化为

fun Date.isTuesday2(): Boolean = getDay() == 2

// 当返回类型可以推到出来的时候，可以省略

fun Date.isTuesday3() = getDay() == 2

// 对于 getter 和 setter ， kotlin 直接使用对应的属性名称即可。 (演示代码提示)

fun Date.isTuesday4() = day == 2
fun testExtFun4() {
    val date = Date()
    val tuesday = date.isTuesday4()
}

/**
 * 四、函数表达式（Function expressions）也叫 lambdas 或者 closures
 * FunExp.kt
 *
 * 五、高阶函数（Higher-order functions ）
 * HiOrder.kt
 *
 * 六、高阶函数,函数表达式,扩展函数 组合一起的代码就看起来非常简洁了
 * DbTran.kt
 *
 * 七、 一些示例代码演示
 *  Demo.kt
 * */

