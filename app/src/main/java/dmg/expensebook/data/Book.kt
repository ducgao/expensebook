package dmg.expensebook.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Book(
  @field:PrimaryKey
  var id: Long? = null,
  var isFinished: Boolean = false
) : RealmObject()