package dmg.expensebook.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dmg.expensebook.R
import dmg.expensebook.core.UIBuilderAdapter
import dmg.expensebook.uicomponent.SelectionUIModel

abstract class SingleSelectionDialogFragment : Fragment() {

  companion object {
    const val SELECTION_KEY = "SELECTION_KEY"
  }

  private val originalSelection: String by lazy { arguments?.getString(SELECTION_KEY) ?: "" }
  private var selection = originalSelection

  private lateinit
  var mainView: View
  private lateinit var recyclerView: RecyclerView
  private lateinit var tvTitle: TextView
  private lateinit var adapter: UIBuilderAdapter

  abstract fun getTitle(): Int
  abstract fun getData(): List<String>

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    mainView = inflater.inflate(R.layout.fragment_selection, container, false)

    bindControls()
    setupRecyclerView()
    bindData()

    return mainView
  }

  private fun bindControls() {
    recyclerView = mainView.findViewById(R.id.recyclerView)
    tvTitle = mainView.findViewById(R.id.tvTitle)
  }

  private fun setupRecyclerView() {
    adapter = UIBuilderAdapter(requireContext())
    recyclerView.adapter = adapter
    recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
  }

  private fun bindData() {
    reloadData()
    tvTitle.setText(getTitle())
  }

  private fun reloadData() {
    val items = getData().map {
      SelectionUIModel(title = it, selected = it == selection) {
        selection = it
        reloadData()
      }
    }

    adapter.updateData(items)
  }
}