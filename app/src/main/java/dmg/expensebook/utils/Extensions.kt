package dmg.expensebook.utils

import android.content.Context
import java.text.DecimalFormat

fun Context.dip(size: Int): Int = (resources.displayMetrics.density * size + 0.5f).toInt()

fun Context.screenWidth(): Int = resources.displayMetrics.widthPixels

fun Long.toPrice(): String = DecimalFormat("#,###").format(this)