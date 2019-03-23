package dmg.expensebook.screen.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderAdapter
import dmg.expensebook.uicomponent.DashBoardUIItem

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
    val header1 = DashBoardUIItem(30000000, 26000000)

    adapter.updateData(listOf(header1))
  }
}