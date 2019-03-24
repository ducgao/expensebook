package dmg.expensebook.uicomponent

import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderItem
import dmg.expensebook.utils.toPrice

class DashBoardUIItem(
  private val income: Long,
  private val spend: Long
) : UIBuilderItem(R.layout.ui_item_dashboard) {

  private val tvIncome: TextView by lazy { view.findViewById<TextView>(R.id.tvIncome) }
  private val tvSpend: TextView by lazy { view.findViewById<TextView>(R.id.tvSpend) }
  private val tvBalance: TextView by lazy { view.findViewById<TextView>(R.id.tvBalance) }

  override fun onBind(index: Int) {
    tvIncome.text = income.toPrice()
    tvSpend.text = spend.toPrice()
    tvBalance.text = (income - spend).toPrice()
  }
}