package com.example.n1pagination.presentation.base.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast

class ToastShower(private val context: Context?) {
    private var currentToast: Toast? = null
    private val toastHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.obj is String) {
                showToast(msg.obj as CharSequence)
            }
        }
    }

    fun showToast(text: CharSequence) {
        if (null == context) {
            return
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            currentToast?.cancel()
            currentToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            currentToast?.show()
        } else {
            Message.obtain(toastHandler, 0, text).sendToTarget()
        }
    }

    fun showToastToQueue(message: String) {
        if (null == context) {
            return
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            Message.obtain(toastHandler, 0, message).sendToTarget()
        }

    }
}