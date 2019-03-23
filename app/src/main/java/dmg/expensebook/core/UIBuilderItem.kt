package dmg.expensebook.core

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class UIBuilderItem(
  @LayoutRes private val layoutId: Int
) {

  protected lateinit var view: View

  fun getViewHolder(context: Context, parent: ViewGroup?): UIBuilderViewHolder {
    view = LayoutInflater.from(context).inflate(layoutId, parent, false)
    return UIBuilderViewHolder(view)
  }

  abstract fun onBind()
}