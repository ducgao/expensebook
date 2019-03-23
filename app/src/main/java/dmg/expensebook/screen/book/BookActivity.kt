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
import dmg.expensebook.screen.book.adapter.ExpenseBookViewPagerAdapter
import dmg.expensebook.utils.INTENT_FILTER_ADD_NEW_BOOK_PAGE

class BookActivity : AppCompatActivity() {

  private val tvTitle: TextView by lazy { findViewById<TextView>(R.id.tvTitle) }
  private val tvAction: TextView by lazy { findViewById<TextView>(R.id.tvAction) }
  private val vpViewPager: ViewPager by lazy { findViewById<ViewPager>(R.id.vpViewPager) }

  private val vpAdapter: ExpenseBookViewPagerAdapter by lazy {
    ExpenseBookViewPagerAdapter(
      supportFragmentManager
    )
  }

  private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      bookData += "1"
      vpAdapter.updateData(bookData)
    }
  }

  private var bookData: List<String> = listOf("1")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_expense_book)

    actionBar?.hide()
    supportActionBar?.hide()

    tvTitle.text = "20/7"
    tvAction.setText(R.string.view_report)

    setUpBroadcastReceiver()
    setUpViewPager()
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
    vpAdapter.updateData(bookData)
    vpViewPager.adapter = vpAdapter
    vpViewPager.setPageTransformer(true, RotateUpTransformer())
  }
}