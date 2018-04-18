package org.goodev.kotlindemo

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewTreeObserver
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import kotlinx.android.synthetic.main.activity_ktx.*

class KtxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktx)

        val myUriString = "url...."
        val uri = Uri.parse(myUriString)
        //Ktx
        val uriKtx = myUriString.toUri()

        // Kotlin
        view.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        view.viewTreeObserver.removeOnPreDrawListener(this)
                        actionToBeTriggered()
                        return true
                    }
                })

        // Ktx
        view.doOnPreDraw {
            actionToBeTriggered()
        }
    }

    private fun actionToBeTriggered() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
