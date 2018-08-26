package libraryapp.kchen52.com.surreypubliclibraryclone.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.android.synthetic.main.fragment_user_new.*
import libraryapp.kchen52.com.surreypubliclibraryclone.BookListActivity
import libraryapp.kchen52.com.surreypubliclibraryclone.HTTPRequest
import libraryapp.kchen52.com.surreypubliclibraryclone.OnFinishListener
import libraryapp.kchen52.com.surreypubliclibraryclone.R
import libraryapp.kchen52.com.surreypubliclibraryclone.book.Book
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager
import okhttp3.Response
import org.json.JSONObject

class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = UserManager.getUser()
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(user.barcode, BarcodeFormat.CODABAR, 1000, 300)
        val barcodeEncoder = BarcodeEncoder()
        barcode_image_view.setImageBitmap(barcodeEncoder.createBitmap(bitMatrix))
        super.onViewCreated(view, savedInstanceState)

        val onFinishListener = object : OnFinishListener {
            override fun onFinish(response: Response) {
                val responseBody = response.body()!!.string()
                val xmlToJson = XmlToJson.Builder(responseBody).build()

                val checkedOutItems = xmlToJson.toJson()!!.optJSONObject("CheckedOutItems")

                val checkedOutBooksArray = ArrayList<Book>()
                val checkedOutBooks = checkedOutItems.optJSONArray("CheckedOutItem")
                for (i in 0 until checkedOutBooks.length()) {
                    checkedOutBooksArray.add(Book(checkedOutBooks.get(i) as JSONObject))
                }
                UserManager.getUser().checkedOutBooks = checkedOutBooksArray

                val count = checkedOutItems.optString("TotalCount")

                checked_out_cardview_icon.text = count
            }

            override fun onFailure(exception: Exception) {
                exception.printStackTrace()
            }
        }
        getCheckedOutBooks(onFinishListener)
        val currentUser = UserManager.getUser()
        user_name.text = currentUser.firstName + " " + currentUser.lastName

        checked_out_cardview.setOnClickListener(View.OnClickListener {
            // TODO: Check if initialized before allowing new intent launch
            val intent = Intent(context, BookListActivity::class.java)
            startActivity(intent)
        })
    }

    private fun getCheckedOutBooks(onFinishListener: OnFinishListener) {
        val url = "https://surrey.bibliocommons.com/api/LoggedInAccount/" + UserManager.getUser().sessionId +
                "/CheckedOutItems/SetPageSize/10/SetPage/0"
        HTTPRequest().makeRequest(url, onFinishListener, null)
    }

}