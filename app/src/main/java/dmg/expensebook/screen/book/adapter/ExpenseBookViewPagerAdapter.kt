package dmg.expensebook.screen.book.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import dmg.expensebook.data.Book
import dmg.expensebook.data.Page
import dmg.expensebook.screen.book.fragment.InitialBookFragment
import dmg.expensebook.screen.book.fragment.PageFragment

class ExpenseBookViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private var book: Book? = null
  private var pages: List<Page> = emptyList()

  fun updateBook(book: Book?) {
    this.book = book
    notifyDataSetChanged()
  }

  fun updatePage(pages: List<Page>) {
    this.pages = pages
    notifyDataSetChanged()
  }

  override fun getItem(p0: Int): Fragment {
    return if (book == null) {
      InitialBookFragment()
    } else {
      PageFragment()
    }
  }

  override fun getCount(): Int {
    return when {
      book == null -> 1
      book!!.isFinished -> pages.size + 2
      else -> pages.size + 1
    }
  }
}