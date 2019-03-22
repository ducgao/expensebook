package dmg.expensebook.book.fragment

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmg.expensebook.R
import dmg.expensebook.utils.INTENT_FILTER_ADD_NEW_BOOK_PAGE
import dmg.expensebook.utils.dip

class AddFragment : Fragment() {

  private lateinit var container: ConstraintLayout
  private lateinit var btnAdd: AppCompatButton

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.fragment_add_page, container, false)

    bindView(view)
    configUI()
    setupViewAction()

    return view
  }

  private fun bindView(view: View) {
    container = view.findViewById(R.id.container)
    btnAdd = view.findViewById(R.id.btnAdd)
  }

  private fun configUI() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      container.elevation = requireContext().dip(4).toFloat()
    }
  }

  private fun setupViewAction() {
    btnAdd.setOnClickListener {
      val intent = Intent(INTENT_FILTER_ADD_NEW_BOOK_PAGE)
      val broadcastManager = LocalBroadcastManager.getInstance(requireContext())
      broadcastManager.sendBroadcast(intent)
    }
  }
}
