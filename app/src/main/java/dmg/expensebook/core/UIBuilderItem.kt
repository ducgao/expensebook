package dmg.expensebook.core

import android.content.Context
import android.view.View
import android.view.ViewGroup

abstract class UIBuildModel {
  abstract fun getViewHolder(context: Context, parent: ViewGroup?): UIBuilderViewHolder
}

abstract class UIBuilderItem<T> {

  lateinit var view: View

  abstract fun onBind(index: Int, item: T)
}