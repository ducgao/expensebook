package dmg.expensebook.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class Category(
  @PrimaryKey val id: Long,
  val name: String,
  val color: String
) : RealmObject()