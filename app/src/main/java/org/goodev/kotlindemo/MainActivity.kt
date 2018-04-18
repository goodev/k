package org.goodev.kotlindemo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Vibrator
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.aw.main_layout.*
import kotlinx.android.synthetic.main.activity_main.*

// 导入 kotlin-android-extensions 生成的代码， 位于 kotlinx.android.synthetic 包下
// 后面的 main 为 productFlavors 的名字， 后面的 activity_main 为 layout 的文件名字
// 上面导入了 aw flavor 的一个布局，

class MainActivity : AppCompatActivity() {

    lateinit var vibrator: Vibrator
    val lazyVibrator: Vibrator by lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (BuildConfig.FLAVOR == "aw") {
            val view = container.inflate(R.layout.main_layout)
            container.addView(view)
        }

        // fab 和 layout
        layout.background = ColorDrawable(Color.BLACK)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment, JavaFragment())
                    .commit()
        }

    }

    override fun onResume() {
        super.onResume()
        if (true) {
            // 延迟初始化
            lazyVibrator.vibrate(100)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
