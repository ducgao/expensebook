package dmg.expensebook.utils

import android.content.Context
import android.content.Intent
import dmg.expensebook.screen.book.BookActivity

fun bookIntent(context: Context, bookId: Long? = null): Intent {
  return Intent(context, BookActivity::class.java).apply {
    putExtra(BOOK_INTENT_BOOK_ID_KEY, bookId)
  }
}