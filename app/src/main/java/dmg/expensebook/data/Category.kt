package dmg.expensebook.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category(
  @field:PrimaryKey
  var id: Long? = null,
  var name: String? = null,
  var color: String? = null
) : RealmObject()