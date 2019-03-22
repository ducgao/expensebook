package dmg.expensebook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import dmg.expensebook.home.HomeActivity

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    actionBar?.hide()
    supportActionBar?.hide()

    Handler().postDelayed({
      startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
      finish()
    }, 1000)
  }
}
