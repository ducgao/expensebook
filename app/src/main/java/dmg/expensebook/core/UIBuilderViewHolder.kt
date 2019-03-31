package dmg.expensebook.core

import android.support.v7.widget.RecyclerView
import android.view.View

open class UIBuilderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  var onBindDelegate: ((Int, UIBuildModel) -> Unit)? = null

  fun onBind(index: Int, item: UIBuildModel) {
    onBindDelegate?.invoke(index, item)
  }
}