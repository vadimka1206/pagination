package com.example.n1pagination.presentation.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.n1pagination.presentation.base.utils.ToastShower

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var toastShower: ToastShower

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toastShower = ToastShower(this)
    }

    fun showDialog(dialogFragment: DialogFragment, tag: String? = null): Boolean {
        val ft = supportFragmentManager.beginTransaction()
        if (null == tag || null == supportFragmentManager.findFragmentByTag(tag)) {
            ft.add(dialogFragment, tag)
            ft.commitNowAllowingStateLoss()
            return true
        }
        return false
    }

    fun showToast(message: CharSequence) {
        toastShower.showToast(message)
    }

    fun showToast(@StringRes stringId: Int) {
        toastShower.showToast(getString(stringId))
    }
}