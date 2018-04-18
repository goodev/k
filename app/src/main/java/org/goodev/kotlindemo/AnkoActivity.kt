package org.goodev.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AnkoActivity : AppCompatActivity(),AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(30)
            var title = editText {
                id = R.id.todo_title
                hintResource = R.string.title_hint
            }

            var desc = editText {
                id = R.id.todo_desc
                hintResource = R.string.description_hint
            }
            button {
                id = R.id.todo_add
                textResource = R.string.add_todo
                onClick { view -> createTodoFrom(title, desc) }
            }
        }
    }

    private fun createTodoFrom(title: EditText, desc: EditText) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun intentJava() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", 5)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)

        //Anko
        startActivity(intentFor<MainActivity>("id" to 5).singleTop())

        startActivity(intentFor<MainActivity>("id" to 5))

        browse("https://github.com")
    }

    // AnkoLogger,  需要继承 AnkoLogger， 默认 Tag 为类名字
    private fun logger() {
        info("London is the capital of Great Britain")
        debug(5) // .toString() method will be executed
        warn(null) // "null" will be printed
    }

    fun toast() {
        toast("Hi there!")
        toast(R.string.description_hint)
        longToast("Wow, such duration")
    }

    fun alertDialog() {
        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton {}
        }.show()
    }
}
