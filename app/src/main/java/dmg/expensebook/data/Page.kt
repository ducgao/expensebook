package dmg.expensebook.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Page(
  @field:PrimaryKey
  var id: Long? = null,
  var bookId: Long? = null
) : RealmObject()