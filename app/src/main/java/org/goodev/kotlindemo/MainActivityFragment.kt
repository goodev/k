package org.goodev.kotlindemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.iterator

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewGroup = view.findViewById<ViewGroup>(R.id.layout)
        for (i in 0 until viewGroup.childCount) {
            val v = viewGroup.getChildAt(i)
            v.visibility = View.VISIBLE
        }

        //ViewGroup.forEach
        viewGroup.forEach {
            it.visibility = View.VISIBLE
        }

        viewGroup.forEachIndexed { index, view ->
            view.visibility = View.VISIBLE
            index
        }

//        viewGroup.children()
//                .filter { it.visibility == View.VISIBLE }
//                .sumBy { it.measuredHeight }

        val iterator = viewGroup.iterator()
        iterator.hasNext()
        iterator.next()
    }
}
