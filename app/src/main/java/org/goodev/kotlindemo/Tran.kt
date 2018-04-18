package org.goodev.kotlindemo

import android.database.sqlite.SQLiteDatabase

/**
 * Created on 2018/4/18.
 */

fun SQLiteDatabase.inTran(func: (SQLiteDatabase) -> Unit) {
    beginTransaction()
    try {
        func(this)
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}

// inline
fun SQLiteDatabase.inTranExt(func: SQLiteDatabase.() -> Unit) {
    beginTransaction()
    try {
        this.func() // 这里没有参数了，而前面的 this 可以去掉，变成 func()
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}

fun test() {
    var db = SQLiteDatabase.create(null)
    db.inTran {
        it.delete("users", "first_name = ?", arrayOf("Jake"))
    }

    db.inTranExt {
        delete("users", "first_name = ?", arrayOf("Jake"))
    }
}

// 可以看到上面两种不同的方式，最终生成的 java 代码是一样的， 值得注意的是
// 这里的函数参数func 每次都会转换为 Java 的匿名内部类，如果在循环中调用，
// 或者项目中大量使用 inTranExt 函数，则会导致很多匿名临时内部类出现，导致
// 内存占用过多，垃圾回收频繁，最终导致 程序卡顿，这个问题可以通过 kotlin
// 的 inline 关键字解决。

// 所以在android 的各种 kotlin 库中，可以看到很多扩展函数都使用了 inline 关键字来修饰


