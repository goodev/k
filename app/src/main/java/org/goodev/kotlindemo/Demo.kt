package org.goodev.kotlindemo

import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created on 2018/4/18.
 */

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    var editor = edit()
    editor.func()
    editor.apply()
}

fun testPref() {
    var pref = PreferenceManager.getDefaultSharedPreferences(null)
    pref.edit {
        putString("key", "value")
        putBoolean("boolKey", false)
        remove("aKey")
    }
}

// 在java 中 两个同名的 set 函数不能同时存在， 可以通过 JvmName 来指定编译为 Java 的函数名字
@JvmName("setString")
fun SharedPreferences.Editor.set(pair: Pair<String, String>) {
    putString(pair.first, pair.second)
}

@JvmName("setBool")
fun SharedPreferences.Editor.set(pair: Pair<String, Boolean>) {
    putBoolean(pair.first, pair.second)
}

fun testPref1() {
    var pref = PreferenceManager.getDefaultSharedPreferences(null)
    pref.edit {
        set("key" to "value")
        set("boolKey" to false)
        remove("aKey")
    }
}

