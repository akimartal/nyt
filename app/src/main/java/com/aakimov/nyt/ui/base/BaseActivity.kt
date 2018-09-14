package com.aakimov.nyt.ui.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.aakimov.nyt.R

open class BaseActivity : AppCompatActivity() {
    protected fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val tr = supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
        if (addToBackStack) tr.addToBackStack(null)
        tr.commit()
    }
}