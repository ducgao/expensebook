package dmg.expensebook.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.book.BookActivity

class HomeActivity : AppCompatActivity() {

  private val tvTitle: TextView by lazy { findViewById<TextView>(R.id.tvTitle) }
  private val tvAction: TextView by lazy { findViewById<TextView>(R.id.tvAction) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    actionBar?.hide()
    supportActionBar?.hide()

    tvTitle.setText(R.string.app_name)
    tvAction.setText(R.string.add)

    setupViewAction()
  }

  private fun setupViewAction() {
    tvAction.setOnClickListener {
      val intent = Intent(this@HomeActivity, BookActivity::class.java)
      startActivity(intent)
    }
  }
}