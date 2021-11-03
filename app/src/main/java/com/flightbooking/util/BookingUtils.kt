package com.flightbooking.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD
import java.text.SimpleDateFormat


/**
 * Created by Nihal Srivastava on 03/11/21.
 */
class BookingUtils {

    companion object {

        var hud: KProgressHUD? = null

        fun showLoader(context: Activity) {
            hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
            hud!!.show()
        }

        fun hideLoader(context: Activity) {
            hud!!.dismiss()
        }

        @SuppressLint("SimpleDateFormat")
        fun dateTime(date: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = sdf.parse(date)
            return SimpleDateFormat("yyyy-MMM-dd\nHH:mm a").format(date)
        }
    }
}