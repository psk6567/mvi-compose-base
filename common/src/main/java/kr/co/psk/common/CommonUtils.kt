package kr.co.psk.common

import android.content.Context
import android.widget.Toast

object CommonUtils {
    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}