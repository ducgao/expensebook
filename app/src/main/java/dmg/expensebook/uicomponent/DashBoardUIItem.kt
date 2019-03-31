package dmg.expensebook.uicomponent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuildModel
import dmg.expensebook.core.UIBuilderItem
import dmg.expensebook.core.UIBuilderViewHolder
import dmg.expensebook.utils.toPrice

data class DashBoardUIModel(
  val income: Long,
  val spend: Long
) : UIBuildModel() {

  private lateinit var rootView: View

  override fun getViewHolder(context: Context, parent: ViewGroup?): UIBuilderViewHolder {
    rootView = LayoutInflater.from(context).inflate(R.layout.ui_item_dashboard, parent, false)
    return UIBuilderViewHolder(rootView).apply {
      onBindDelegate = { index, item ->
        uiItem.onBind(index, item = item as DashBoardUIModel)
      }
    }
  }

  private val uiItem: DashBoardUIItem by lazy { DashBoardUIItem().apply { view = rootView } }
}

class DashBoardUIItem : UIBuilderItem<DashBoardUIModel>() {

  private val tvIncome: TextView by lazy { view.findViewById<TextView>(R.id.tvIncome) }
  private val tvSpend: TextView by lazy { view.findViewById<TextView>(R.id.tvSpend) }
  private val tvBalance: TextView by lazy { view.findViewById<TextView>(R.id.tvBalance) }

  override fun onBind(index: Int, item: DashBoardUIModel) {
    tvIncome.text = item.income.toPrice()
    tvSpend.text = item.spend.toPrice()
    tvBalance.text = (item.income - item.spend).toPrice()
  }
}