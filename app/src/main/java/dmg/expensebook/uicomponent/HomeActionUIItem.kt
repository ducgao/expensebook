package dmg.expensebook.uicomponent

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuildModel
import dmg.expensebook.core.UIBuilderItem
import dmg.expensebook.core.UIBuilderViewHolder
import dmg.expensebook.utils.dip
import dmg.expensebook.utils.screenWidth

data class HomeActionUIModel(
  @DrawableRes val icon: Int,
  @StringRes val title: Int,
  val action: () -> Unit
) : UIBuildModel() {

  private lateinit var rootView: View

  override fun getViewHolder(context: Context, parent: ViewGroup?): UIBuilderViewHolder {
    rootView = LayoutInflater.from(context).inflate(R.layout.ui_item_home_action, parent, false)
    return UIBuilderViewHolder(rootView).apply {
      onBindDelegate = { index, item ->
        uiItem.onBind(index, item = item as HomeActionUIModel)
      }
    }
  }

  private val uiItem: HomeActionUIItem by lazy { HomeActionUIItem().apply { view = rootView } }
}

class HomeActionUIItem : UIBuilderItem<HomeActionUIModel>() {

  private val container: ConstraintLayout by lazy { view.findViewById<ConstraintLayout>(R.id.container) }

  private val tvTitle: TextView by lazy { view.findViewById<TextView>(R.id.tvTitle) }
  private val ivIcon: ImageView by lazy { view.findViewById<ImageView>(R.id.ivIcon) }

  override fun onBind(index: Int, item: HomeActionUIModel) {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      container.elevation = container.context.dip(4).toFloat()
    }

    container.setOnClickListener {
      item.action.invoke()
    }

    container.layoutParams.apply {
      this as GridLayoutManager.LayoutParams
      val context = container.context
      width = (context.screenWidth() - context.dip(16) * 3) / 2
      leftMargin = if (index % 2 != 0) context.dip(16) else context.dip(8)
    }

    ivIcon.setImageResource(item.icon)
    tvTitle.setText(item.title)
  }
}