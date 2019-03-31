package dmg.expensebook.core

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class UIBuilderAdapter(private val context: Context) : RecyclerView.Adapter<UIBuilderViewHolder>() {

  private var data: List<UIBuildModel> = emptyList()

  fun updateData(newData: List<UIBuildModel>) {
    this.data = newData
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UIBuilderViewHolder {
    return this.data[p1].getViewHolder(context, p0)
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  override fun getItemCount(): Int {
    return this.data.size
  }

  override fun onBindViewHolder(p0: UIBuilderViewHolder, p1: Int) {
    p0.onBind(p1, this.data[p1])
  }
}