package dmg.expensebook.uicomponent

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuildModel
import dmg.expensebook.core.UIBuilderItem
import dmg.expensebook.core.UIBuilderViewHolder

data class SelectionUIModel(
  val title: String,
  @ColorRes val colorTag: Int? = null,
  val selected: Boolean = false,
  val action: () -> Unit
) : UIBuildModel() {

  private lateinit var rootView: View

  override fun getViewHolder(context: Context, parent: ViewGroup?): UIBuilderViewHolder {
    rootView = LayoutInflater.from(context).inflate(R.layout.ui_item_selection, parent, false)
    return UIBuilderViewHolder(rootView).apply {
      onBindDelegate = { index, item ->
        uiItem.onBind(index, item = item as SelectionUIModel)
      }
    }
  }

  private val uiItem: SelectionUIItem by lazy { SelectionUIItem().apply { view = rootView } }
}

class SelectionUIItem : UIBuilderItem<SelectionUIModel>() {

  private val container: LinearLayout by lazy { view.findViewById<LinearLayout>(R.id.container) }

  private val vTag: View by lazy { view.findViewById<View>(R.id.vTag) }
  private val tvTitle: TextView by lazy { view.findViewById<TextView>(R.id.tvTitle) }

  override fun onBind(index: Int, item: SelectionUIModel) {
    container.setOnClickListener {
      item.action.invoke()
    }

    if (item.colorTag == null) {
      vTag.visibility = View.GONE
    } else {
      vTag.visibility = View.VISIBLE
      vTag.setBackgroundColor(ContextCompat.getColor(vTag.context, item.colorTag))
    }

    tvTitle.text = item.title

    if (item.selected) {
      container.setBackgroundColor(ContextCompat.getColor(container.context, R.color.colorPrimary))
      tvTitle.setTextColor(ContextCompat.getColor(tvTitle.context, android.R.color.white))
    } else {
      container.setBackgroundColor(ContextCompat.getColor(container.context, android.R.color.transparent))
      tvTitle.setTextColor(ContextCompat.getColor(tvTitle.context, android.R.color.black))
    }
  }
}