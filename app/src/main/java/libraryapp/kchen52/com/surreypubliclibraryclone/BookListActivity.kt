package libraryapp.kchen52.com.surreypubliclibraryclone

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_booklist.*
import libraryapp.kchen52.com.surreypubliclibraryclone.adapter.BookAdapter
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager

class BookListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booklist)

        rv_book_list.layoutManager = LinearLayoutManager(this)
        rv_book_list.adapter = BookAdapter(UserManager.getUser().checkedOutBooks, this)
    }
}