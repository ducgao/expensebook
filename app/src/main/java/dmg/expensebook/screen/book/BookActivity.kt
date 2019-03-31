package dmg.expensebook.screen.book

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.eftimoff.viewpagertransformers.RotateUpTransformer
import dmg.expensebook.R
import dmg.expensebook.data.Book
import dmg.expensebook.screen.book.adapter.ExpenseBookViewPagerAdapter
import dmg.expensebook.utils.BOOK_INTENT_BOOK_ID_KEY
import dmg.expensebook.utils.INTENT_FILTER_ADD_NEW_BOOK_PAGE

class BookActivity : AppCompatActivity() {

  private val tvTitle: TextView by lazy { findViewById<TextView>(R.id.tvTitle) }
  private val tvAction: TextView by lazy { findViewById<TextView>(R.id.tvAction) }
  private val vpViewPager: ViewPager by lazy { findViewById<ViewPager>(R.id.vpViewPager) }

  private val bookId: Long by lazy { intent.getLongExtra(BOOK_INTENT_BOOK_ID_KEY, -1) }

  private var bookData: Book? = null

  private val vpAdapter: ExpenseBookViewPagerAdapter by lazy {
    ExpenseBookViewPagerAdapter(
      supportFragmentManager
    )
  }

  private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//      bookData += "1"
//      vpAdapter.updateBook(bookData)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expense_book)

    actionBar?.hide()
    supportActionBar?.hide()

    tvAction.setText(R.string.view_report)
    tvTitle.text = if (bookId == -1L) {
      getString(R.string.book_new)
    } else {
      getString(R.string.month_april)
    }

    setUpBroadcastReceiver()
    setUpViewPager()
    loadData()
  }

  override fun onDestroy() {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    super.onDestroy()
  }

  private fun setUpBroadcastReceiver() {
    val filter = IntentFilter(INTENT_FILTER_ADD_NEW_BOOK_PAGE)
    LocalBroadcastManager.getInstance(this@BookActivity).registerReceiver(broadcastReceiver, filter)
  }

  private fun setUpViewPager() {
    vpViewPager.adapter = vpAdapter
    vpViewPager.setPageTransformer(true, RotateUpTransformer())
  }

  private fun loadData() {
    if (bookId == -1L) {
      loadNewData()
    } else {
      loadOldData()
    }
  }

  private fun loadNewData() {
    bookData = null
    vpAdapter.updateBook(bookData)
  }

  private fun loadOldData() {
    TODO("load old data is not implemented yet")
  }
}

