package dmg.expensebook.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Entry(
  @field:PrimaryKey
  var id: Long? = null,
  var pageId: Long? = null,
  var categoryId: Long? = null,
  var description: String? = null,
  var amount: Long? = null
) : RealmObject()