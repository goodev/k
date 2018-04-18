package org.goodev.kotlindemo

/**
 * 函数表达式,
 * 注意下面的变量 sum 是一个函数变量，其类型是
 *  (Int, Int) -> Int ，有两个Int 类型的参数，返回一个 Int 类型的值，
 *  其函数体是 x, y -> x + y
 *  其功能就是返回 x + y 的值。
 *
 */
val sum: (Int, Int) -> Int = { x, y -> x + y }

// x,y 是这个 sum 函数的参数， 参数的类型也可以放到变量声明的地方
val sum1 = { x: Int, y: Int -> x - y }

// 下面是调用 sum 这个函数
val value = sum(1, 2)

// 函数表达式编译为 Java 类后会转换为 类存在。 Kotlin Bytecode



