package dmg.expensebook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import dmg.expensebook.data.Category
import dmg.expensebook.data.Database
import dmg.expensebook.screen.home.HomeActivity
import dmg.expensebook.utils.isFirstTimeOpenApp

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Database.init(this@SplashActivity)

    actionBar?.hide()
    supportActionBar?.hide()

    setupData()

    Handler().postDelayed({
      startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
      finish()
    }, 1000)
  }

  private fun setupData() {
    val isFirstTimeOpenApp = this@SplashActivity.isFirstTimeOpenApp()
    if (isFirstTimeOpenApp) {
      val eatCate = Category(0, "", "")
      val trafficCate = Category(1, "", "")
      val personalizeCate = Category(2, "", "")
      val clothingCate = Category(3, "", "")
      val otherCate = Category(4, "", "")

      Database.setCategories(listOf(eatCate, trafficCate, personalizeCate, clothingCate, otherCate))
    }
  }
}
