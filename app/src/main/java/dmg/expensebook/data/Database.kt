package dmg.expensebook.data

import android.content.Context
import io.realm.Realm

object Database {
  fun init(context: Context) {
    Realm.init(context)
  }

  fun setCategories(categoryList: List<Category>) {
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(Category::class.java)
    realm.insertOrUpdate(categoryList)
    realm.commitTransaction()
  }

  fun getCategories(): List<Category> {
    val realm = Realm.getDefaultInstance()
    return realm.where(Category::class.java).findAll()
  }
}