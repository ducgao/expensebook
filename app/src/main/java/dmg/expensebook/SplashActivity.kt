package dmg.expensebook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import dmg.expensebook.data.Category
import dmg.expensebook.data.Database
import dmg.expensebook.screen.home.HomeActivity
import dmg.expensebook.utils.COLOR
import dmg.expensebook.utils.isFirstTimeOpenApp

class SplashActivity : AppCompatActivity() {

  private var isReadyToMove: Boolean = false
  private val handler: Handler by lazy { Handler() }
  private val moveRunnable: Runnable = Runnable {
    move()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Database.init(this@SplashActivity)

    actionBar?.hide()
    supportActionBar?.hide()

    setupData()

    handler.postDelayed(moveRunnable, 1000)
  }

  private fun setupData() {
    val isFirstTimeOpenApp = this@SplashActivity.isFirstTimeOpenApp()
    if (isFirstTimeOpenApp) {
      val eatCate = Category(0, getString(R.string.cate_eat), COLOR[0])
      val trafficCate = Category(1, getString(R.string.cate_traffic), COLOR[1])
      val personalizeCate = Category(2, getString(R.string.cate_personalize), COLOR[2])
      val clothingCate = Category(3, getString(R.string.cate_clothing), COLOR[3])
      val otherCate = Category(4, getString(R.string.cate_other), COLOR[4])

      Database.setCategories(listOf(eatCate, trafficCate, personalizeCate, clothingCate, otherCate))
    }

    move()
  }

  private fun move() {
    if (isReadyToMove) {
      handler.removeCallbacks(moveRunnable)
      startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
      finish()
    } else {
      isReadyToMove = true
    }
  }
}
