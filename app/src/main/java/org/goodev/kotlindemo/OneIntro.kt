package org.goodev.kotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * 回顾下基本内容。
 *
 * Kotlin 吸收了各种编程语言的优点而创建的。
 * Kotlin  代码看起来更简洁。
 * Write less, Do more.
 */

// nullability , null 成为一个类型，在声明的时候就指定了该变量是 null 还是 非null。
// 这样用的时候就可以避免 NEP 了。在 Java 里面会发现各种 NPE bug，然后在代码中
// 通过 if(a != null) do something 的方式来判断 null。

var output: String = "a" // 类似于 @NotNull 但是编译器强制检查
//output = null   // Compilation error


val name: String? = null    // Nullable type 类似于 @Nullable
//println(name.length())      // Compilation error

// Create a POJO with getters, setters, equals(), hashCode(), toString(), and copy() with a single line:
data class User(val name: String, val email: String)

// Java
//button.setOnClickListener(new View.OnClickListener(){
//    @Override
//    public void onClick(View v){
//        doSomething();
//    }
//});

// Kotlin
//button.setOnClickListener { doSomething() }

// Extend ViewGroup class with inflate function
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


// Call inflate directly on the ViewGroup instance
fun onCreateViewHolder(parent: ViewGroup, viewType: Int){
    val v = parent.inflate(R.layout.content_main)
}
