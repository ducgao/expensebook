package dmg.expensebook.uicomponent

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderItem
import dmg.expensebook.utils.dip
import dmg.expensebook.utils.screenWidth

class HomeActionUIItem(
  @DrawableRes private val icon: Int,
  @StringRes private val title: Int
) : UIBuilderItem(R.layout.ui_item_home_action) {

  private val container: ConstraintLayout by lazy { view.findViewById<ConstraintLayout>(R.id.container) }

  private val tvTitle: TextView by lazy { view.findViewById<TextView>(R.id.tvTitle) }
  private val ivIcon: ImageView by lazy { view.findViewById<ImageView>(R.id.ivIcon) }

  override fun onBind(index: Int) {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      container.elevation = container.context.dip(4).toFloat()
    }

    container.layoutParams.apply {
      this as GridLayoutManager.LayoutParams
      val context = container.context
      width = (context.screenWidth() - context.dip(16) * 3) / 2
      leftMargin = if (index % 2 != 0) context.dip(16) else context.dip(8)
    }

    ivIcon.setImageResource(icon)
    tvTitle.setText(title)
  }
}