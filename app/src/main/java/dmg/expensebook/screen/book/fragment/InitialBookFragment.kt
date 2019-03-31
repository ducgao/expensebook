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

class InitialBookFragment : Fragment() {

  private lateinit var container: ConstraintLayout
  private lateinit var btnSelectMonthYear: AppCompatButton

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.fragment_initial_book, container, false)

    bindView(view)
    configUI()
    setupViewAction()

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

  private fun setupViewAction() {
    btnSelectMonthYear.setOnClickListener {
      showMonthYearSelectionDialog()
    }
  }

  private fun showMonthYearSelectionDialog() {
    val dialog = MonthYearSelectDialogFragment.create(3, 2019)
    dialog.show(fragmentManager, InitialBookFragment::class.java.name)
  }
}
