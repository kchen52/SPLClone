package libraryapp.kchen52.com.surreypubliclibraryclone.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import libraryapp.kchen52.com.surreypubliclibraryclone.R
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager

class UserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO: Perform check that user is still authenticated.
        // If they've checked off a "keep me logged in" setting, re-auth them if they not
        // Else, boot them to the login page
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = UserManager.getUser()
        var barcodeIV = view.findViewById<ImageView>(R.id.barcode_image_view)
        var multiFormatWriter = MultiFormatWriter()
        var bitMatrix = multiFormatWriter.encode(user.barcode, BarcodeFormat.CODABAR, 400, 200)
        var barcodeEncoder = BarcodeEncoder()
        barcodeIV.setImageBitmap(barcodeEncoder.createBitmap(bitMatrix))
        super.onViewCreated(view, savedInstanceState)
    }

}