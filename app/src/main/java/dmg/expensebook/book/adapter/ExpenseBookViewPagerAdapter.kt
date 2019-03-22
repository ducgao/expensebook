package dmg.expensebook.book.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import dmg.expensebook.book.fragment.AddFragment
import dmg.expensebook.book.fragment.PageFragment

class ExpenseBookViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private var data: List<String> = emptyList()

  fun updateData(data: List<String>) {
    this.data = data
    notifyDataSetChanged()
  }

  override fun getItem(p0: Int): Fragment {
    return if (p0 == data.lastIndex) {
      AddFragment()
    } else {
      PageFragment()
    }
  }

  override fun getCount(): Int = data.size
}