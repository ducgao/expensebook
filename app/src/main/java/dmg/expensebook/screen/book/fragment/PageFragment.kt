package dmg.expensebook.screen.book.fragment

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmg.expensebook.R
import dmg.expensebook.utils.dip

class PageFragment : Fragment() {

  private lateinit var container: ConstraintLayout

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.fragment_book_page, container, false)

    bindView(view)
    configUI()

    return view
  }

  private fun bindView(view: View) {
    container = view.findViewById(R.id.container)
  }

  private fun configUI() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      container.elevation = context!!.dip(4).toFloat()
    }
  }
}