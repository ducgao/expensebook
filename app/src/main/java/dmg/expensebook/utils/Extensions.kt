package dmg.expensebook.utils

import android.content.Context
import java.text.DecimalFormat

fun Context.dip(size: Int): Int = (resources.displayMetrics.density * size + 0.5f).toInt()

fun Context.screenWidth(): Int = resources.displayMetrics.widthPixels

fun Context.isFirstTimeOpenApp(): Boolean {
  val prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
  return prefs.getBoolean(FIRST_TIME_OPEN_APP_KEY, false)
}

fun Context.setFirstTimeOpenApp() {
  val prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
  val editor = prefs.edit()
  editor.putBoolean(FIRST_TIME_OPEN_APP_KEY, true)
  editor.apply()
}

fun Long.toPrice(): String = DecimalFormat("#,###").format(this)