package dmg.expensebook.utils

import android.content.Context

fun Context.dip(size: Int): Int = (resources.displayMetrics.density * size + 0.5f).toInt()