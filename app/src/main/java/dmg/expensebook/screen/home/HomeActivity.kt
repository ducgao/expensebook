package dmg.expensebook.screen.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderAdapter
import dmg.expensebook.data.Database
import dmg.expensebook.uicomponent.DashBoardUIModel
import dmg.expensebook.uicomponent.HomeActionUIModel
import dmg.expensebook.utils.MonthYearSelectDialogFragment

class HomeActivity : AppCompatActivity() {

  private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

  private val adapter: UIBuilderAdapter by lazy { UIBuilderAdapter(this@HomeActivity) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    actionBar?.hide()
    supportActionBar?.hide()

    setUpRecyclerView()
    buildUI()
  }

  override fun onResume() {
    super.onResume()
    val data = Database.getCategories().toList()
    val something = data[0].color
  }

  private fun setUpRecyclerView() {
    recyclerView.adapter = adapter
    recyclerView.layoutManager = GridLayoutManager(this@HomeActivity, 2).apply {
      spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(p0: Int): Int {
          return if (p0 == 0) 2 else 1
        }
      }
    }
  }

  private fun buildUI() {
    val header = DashBoardUIModel(30000000, 26000000)

    val actionAdd = HomeActionUIModel(R.drawable.ic_add, R.string.add_book) {
      showNewBookDialog()
    }

    val actionSettings = HomeActionUIModel(R.drawable.ic_setting, R.string.settings) {

    }

    adapter.updateData(listOf(header, actionAdd, actionSettings))
  }

  private fun showNewBookDialog() {
    val dialog = MonthYearSelectDialogFragment.create(this@HomeActivity) { selectedMonth, selectedYear ->
    }

    dialog.show(supportFragmentManager, HomeActivity::class.java.name)
  }
}