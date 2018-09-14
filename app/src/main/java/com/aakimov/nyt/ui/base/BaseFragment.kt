package com.aakimov.nyt.ui.base

import android.support.annotation.IdRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.aakimov.nyt.R

open class BaseFragment : Fragment() {

    fun Fragment.getStringArg(key: String): String {
        return arguments!!.getString(key)
    }

    protected fun inform(@IdRes id: Int, text: String) {
        val root = view!!.findViewById<View>(id)
        val snack = Snackbar.make(root, text, Snackbar
                .LENGTH_SHORT)
        val view = snack.view
        view.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorAccent, null))
        snack.show()
    }

    protected fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val tr = fragmentManager?.beginTransaction()
                ?.replace(R.id.content, fragment)
        if (addToBackStack) tr?.addToBackStack(null)
        tr?.commit()
    }
}