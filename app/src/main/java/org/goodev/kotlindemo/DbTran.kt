package org.goodev.kotlindemo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

/**
 * 数据库事物提交样板代码
 */

fun doDeleteInTransaction(db: SQLiteDatabase) {
    db.beginTransaction();
    try {
        // 只有下面这一行代码是主要的
        db.delete("users", "first_name = ?", arrayOf("Jake"));
        db.setTransactionSuccessful();
    } finally {
        db.endTransaction();
    }
}

fun doUpdateInTransaction(db: SQLiteDatabase) {
    db.beginTransaction();
    try {
        val cv = ContentValues()
        // 只有下面这一行代码是主要的
        db.update("users", cv, "first_name = ?", arrayOf("Jake"));
        db.setTransactionSuccessful();
    } finally {
        db.endTransaction();
    }
}

// 可以看到上面在做数据库事物操作的时候， beginTransaction、setTransactionSuccessful、
// endTransaction 这三个函数都需要在正确的地方调用，在写代码或者重构代码的时候，
// 这里容易出错，并且出错后还不容易发现。
// Kotlin 可以避免这种情况

fun SQLiteDatabase.inTransaction(func: (SQLiteDatabase) -> Unit) {
    beginTransaction()
    try {
        func(this)
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}

// 上面 inTransaction 函数是 SQLiteDatabase 类的一个扩张函数，所以在函数体里面可以直接访问 上面
// 三个函数， 然后改函数的参数是一个函数，类型为 (SQLiteDatabase) -> Unit，也就是 SQLiteDatabase
// 为这个参数函数 func 的参数，没有返回值
fun testTransactionExt() {
    var db = SQLiteDatabase.create(null)
    db.inTransaction {
        it.delete("users", "first_name = ?", arrayOf("Jake"))
    }
    // 如果函数只有一个参数，可以用 it 代替，并且不用指定这个参数名字了
    db.inTransaction {
        it.update("users", ContentValues(), "first_name = ?", arrayOf("Jake"));
    }

    // 上面的简化形式，等价于下面的形式， inTransaction 参数是个函数，这里是个匿名函数
    val paramFun = fun(temp: SQLiteDatabase) {
        temp.delete("users", "first_name = ?", arrayOf("Jake"))
    }
    db.inTransaction(paramFun)

    // 上面看起来更加清晰一点，但是代码比较冗余，可以把变量 paramFun 省略
    db.inTransaction(fun(temp: SQLiteDatabase) {
        temp.delete("users", "first_name = ?", arrayOf("Jake"))
    })

    // 参数的类型也可以省略
    db.inTransaction(fun(temp) {
        temp.delete("users", "first_name = ?", arrayOf("Jake"))
    })
    // 只有一个参数呢， 函数声明也可以省略
    db.inTransaction({
        it.delete("users", "first_name = ?", arrayOf("Jake"))
    })

    // 当最后一个参数为函数表达式的时候，可以放到括号() 外面
    db.inTransaction() {
        it.delete("users", "first_name = ?", arrayOf("Jake"))
    }

    // 只有括号() 的时候，括号可以省略，
    db.inTransaction {
        it.delete("users", "first_name = ?", arrayOf("Jake"))
    }

}

// 上面每次写代码的时候， 都要写一次 it 这个变量，然后是最终的执行代码， 还不够简化。
// 注意下面 把 参数func 的类型从 (SQLiteDatabase) -> Unit 修改为
// SQLiteDatabase.() -> Unit 了，也就是说，这个参数也是一个扩展函数，这样就可以在函数体
// 内直接使用 SQLiteDatabase 的 函数了。

fun SQLiteDatabase.inTransaction1(func: SQLiteDatabase.() -> Unit) {
    beginTransaction()
    try {
        this.func() // 这里没有参数了，而前面的 this 可以去掉，变成 func()
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}

fun testTran() {
    var db = SQLiteDatabase.create(null)
    db.inTransaction1 {
        delete("users", "first_name = ?", arrayOf("Jake"))
        //update("users", ContentValues(), "first_name = ?", arrayOf("Jake"));
    }
}

// 看看 上面两个 inTransaction1 转换为 Java 代码有啥区别  Tran.kt


