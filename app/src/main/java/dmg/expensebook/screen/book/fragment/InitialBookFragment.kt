package dmg.expensebook.screen.book.fragment

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmg.expensebook.R
import dmg.expensebook.utils.MonthYearSelectDialogFragment
import dmg.expensebook.utils.dip
import java.util.Calendar

class InitialBookFragment : Fragment() {

  private lateinit var container: ConstraintLayout
  private lateinit var btnSelectMonthYear: AppCompatButton

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.fragment_initial_book, container, false)

    bindView(view)
    configUI()
    showConfigDialog()

    return view
  }

  private fun bindView(view: View) {
    container = view.findViewById(R.id.container)
    btnSelectMonthYear = view.findViewById(R.id.btnSelectMonthYear)
  }

  private fun configUI() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      container.elevation = requireContext().dip(4).toFloat()
    }
  }

  private fun showConfigDialog() {
    showMonthYearSelectionDialog()
  }

  private fun showMonthYearSelectionDialog() {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = when (c.get(Calendar.MONTH)) {
      0 -> getString(R.string.month_january)
      1 -> getString(R.string.month_february)
      2 -> getString(R.string.month_march)
      3 -> getString(R.string.month_april)
      4 -> getString(R.string.month_may)
      5 -> getString(R.string.month_june)
      6 -> getString(R.string.month_july)
      7 -> getString(R.string.month_august)
      8 -> getString(R.string.month_september)
      9 -> getString(R.string.month_october)
      10 -> getString(R.string.month_november)
      else -> getString(R.string.month_december)
    }
    val dialog = MonthYearSelectDialogFragment.create(month, "$year") { selectedMonth, selectedYear ->

    }
    dialog.isCancelable = false
    dialog.show(fragmentManager, InitialBookFragment::class.java.name)
  }
}
