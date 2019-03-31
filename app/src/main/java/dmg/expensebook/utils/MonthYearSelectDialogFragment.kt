package dmg.expensebook.utils

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

class MonthYearSelectDialogFragment : DialogFragment() {

  companion object {
    private const val SELECTION_MONTH_KEY = "SELECTION_MONTH_KEY"
    private const val SELECTION_YEAR_KEY = "SELECTION_YEAR_KEY"

    @JvmStatic
    fun create(month: Int, year: Int): MonthYearSelectDialogFragment {
      return MonthYearSelectDialogFragment().apply {
        arguments = Bundle().apply {
          putInt(SELECTION_MONTH_KEY, month)
          putInt(SELECTION_YEAR_KEY, year)
        }
      }
    }
  }

  private lateinit var vpViewPager: ViewPager
  private lateinit var btnBack: Button
  private lateinit var btnDone: Button

  private val currentMonth: Int by lazy { arguments?.getInt(SELECTION_MONTH_KEY, -1) ?: -1 }
  private val currentYear: Int by lazy { arguments?.getInt(SELECTION_YEAR_KEY, -1) ?: -1 }

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
    vpViewPager.adapter = MonthYearSelectViewPagerAdapter(childFragmentManager)
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

  override fun getItem(p0: Int): Fragment {
    return if (p0 == 0) {
      MonthSelectFragment.create("July")
    } else {
      YearSelectFragment.create("2019")
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