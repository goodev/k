package org.goodev.kotlindemo

/**
 * 高阶函数（Higher-order functions ）
 * 参数为函数或者返回值为函数的函数就被称之为高阶函数
 *
 */

// apply 这个函数有参数参数， one two 两个参数为 Int 类型对象
// 而 func 参数为 (Int, Int) -> Int 类型的函数
fun apply(one: Int, two: Int, func: (Int, Int) -> Int): Int {
    return func(one, two)
}

// 使用 apply 函数
val sum12 = apply(1, 2, { x, y -> x + y })
val difference = apply(1, 2, { x, y -> x - y })
