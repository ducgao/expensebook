package dmg.expensebook.uicomponent

import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderItem

class DashBoardUIItem(
  private val income: Long,
  private val spend: Long
) : UIBuilderItem(R.layout.ui_item_dashboard) {

  private val tvIncome: TextView by lazy { view.findViewById<TextView>(R.id.tvIncome) }
  private val tvSpend: TextView by lazy { view.findViewById<TextView>(R.id.tvSpend) }
  private val tvBalance: TextView by lazy { view.findViewById<TextView>(R.id.tvBalance) }

  override fun onBind() {
    tvIncome.text = income.toString()
    tvSpend.text = spend.toString()
    tvBalance.text = (income - spend).toString()
  }
}