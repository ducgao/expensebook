package dmg.expensebook.utils

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dmg.expensebook.R
import java.util.Calendar

class MonthYearSelectDialogFragment : DialogFragment() {

  companion object {
    private const val SELECTION_MONTH_KEY = "SELECTION_MONTH_KEY"
    private const val SELECTION_YEAR_KEY = "SELECTION_YEAR_KEY"

    @JvmStatic
    fun create(month: String, year: String, action: (String, String) -> Unit): MonthYearSelectDialogFragment {
      return MonthYearSelectDialogFragment().apply {
        arguments = Bundle().apply {
          putString(SELECTION_MONTH_KEY, month)
          putString(SELECTION_YEAR_KEY, year)
        }
        callback = action
      }
    }

    @JvmStatic
    fun create(context: Context, action: (String, String) -> Unit): MonthYearSelectDialogFragment {
      val c = Calendar.getInstance()
      val year = c.get(Calendar.YEAR).toString()
      val month = when (c.get(Calendar.MONTH)) {
        0 -> context.getString(R.string.month_january)
        1 -> context.getString(R.string.month_february)
        2 -> context.getString(R.string.month_march)
        3 -> context.getString(R.string.month_april)
        4 -> context.getString(R.string.month_may)
        5 -> context.getString(R.string.month_june)
        6 -> context.getString(R.string.month_july)
        7 -> context.getString(R.string.month_august)
        8 -> context.getString(R.string.month_september)
        9 -> context.getString(R.string.month_october)
        10 -> context.getString(R.string.month_november)
        else -> context.getString(R.string.month_december)
      }

      return MonthYearSelectDialogFragment().apply {
        arguments = Bundle().apply {
          putString(SELECTION_MONTH_KEY, month)
          putString(SELECTION_YEAR_KEY, year)
        }
        callback = action
      }
    }
  }

  lateinit var callback: (String, String) -> Unit

  private lateinit var vpViewPager: ViewPager
  private lateinit var btnBack: Button
  private lateinit var btnDone: Button

  private val currentMonth: String by lazy { arguments?.getString(SELECTION_MONTH_KEY) ?: "" }
  private val currentYear: String by lazy { arguments?.getString(SELECTION_YEAR_KEY) ?: "" }

  private lateinit var mainView: View

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    mainView = inflater.inflate(R.layout.fragment_month_year_selection, container, false)

    bindControls()
    setupViewPager()
    setupAction()

    return mainView
  }

  private fun bindControls() {
    vpViewPager = mainView.findViewById(R.id.vpViewPager)
    btnBack = mainView.findViewById(R.id.btnBack)
    btnDone = mainView.findViewById(R.id.btnDone)
  }

  private fun setupViewPager() {
    vpViewPager.layoutParams.apply {
      val displayWidth = requireContext().screenWidth() - requireContext().dip(48)
      width = displayWidth
      height = (displayWidth * 1.5).toInt()
    }
    vpViewPager.adapter = MonthYearSelectViewPagerAdapter(childFragmentManager).apply {
      currentMonth = this@MonthYearSelectDialogFragment.currentMonth
      currentYear = this@MonthYearSelectDialogFragment.currentYear
    }
    vpViewPager.addOnPageChangeListener(object : OnPageChangeListener {
      override fun onPageScrollStateChanged(p0: Int) {}

      override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

      override fun onPageSelected(p0: Int) {
        if (p0 == 0) {
          btnDone.isEnabled = false
          btnBack.isEnabled = false
        } else {
          btnDone.isEnabled = true
          btnBack.isEnabled = true
        }
      }
    })
  }

  private fun setupAction() {
    btnDone.setOnClickListener {
      callback.invoke(currentMonth, currentYear)
      dismiss()
    }
    btnBack.setOnClickListener {
      vpViewPager.setCurrentItem(0, true)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    vpViewPager.clearOnPageChangeListeners()
  }
}

class MonthYearSelectViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

  lateinit var currentMonth: String
  lateinit var currentYear: String

  override fun getItem(p0: Int): Fragment {
    return if (p0 == 0) {
      MonthSelectFragment.create(currentMonth)
    } else {
      YearSelectFragment.create(currentYear)
    }
  }

  override fun getCount(): Int = 2
}

class MonthSelectFragment : SingleSelectionDialogFragment() {

  companion object {
    fun create(selection: String): MonthSelectFragment {
      return MonthSelectFragment().apply {
        arguments = Bundle().apply {
          putString(SELECTION_KEY, selection)
        }
      }
    }
  }

  override fun getTitle(): Int = R.string.select_month

  override fun getData(): List<String> {
    val jan = getString(R.string.month_january)
    val feb = getString(R.string.month_february)
    val mar = getString(R.string.month_march)
    val apr = getString(R.string.month_april)
    val may = getString(R.string.month_may)
    val jun = getString(R.string.month_june)
    val jul = getString(R.string.month_july)
    val aug = getString(R.string.month_august)
    val sep = getString(R.string.month_september)
    val oct = getString(R.string.month_october)
    val nov = getString(R.string.month_november)
    val dec = getString(R.string.month_december)

    return listOf(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec)
  }
}

class YearSelectFragment : SingleSelectionDialogFragment() {

  companion object {
    fun create(selection: String): YearSelectFragment {
      return YearSelectFragment().apply {
        arguments = Bundle().apply {
          putString(SELECTION_KEY, selection)
        }
      }
    }
  }

  override fun getTitle(): Int = R.string.select_year

  override fun getData(): List<String> {
    val base = 2019
    var data = listOf("$base")
    for (i in 1..20) {
      data += "${2019 + i}"
    }
    return data
  }
}