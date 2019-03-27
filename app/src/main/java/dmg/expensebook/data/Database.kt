package dmg.expensebook.data

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

object Database {

  private val config: RealmConfiguration by lazy {
    RealmConfiguration.Builder()
      .name("default.realm")
      .schemaVersion(1)
      .deleteRealmIfMigrationNeeded()
      .build()
  }

  fun init(context: Context) {
    Realm.init(context)
    Realm.getInstance(config).close()
  }

  fun setCategories(categoryList: List<Category>) {
    val realm = Realm.getInstance(config)
    realm.beginTransaction()
    realm.delete(Category::class.java)
    realm.copyToRealm(categoryList)
    realm.commitTransaction()
  }

  fun getCategories(): RealmResults<Category> {
    val realm = Realm.getInstance(config)
    return realm.where(Category::class.java).findAll()
  }
}