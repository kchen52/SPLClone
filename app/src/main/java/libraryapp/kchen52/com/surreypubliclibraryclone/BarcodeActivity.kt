package libraryapp.kchen52.com.surreypubliclibraryclone

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

import kotlinx.android.synthetic.main.activity_barcode.*
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager

class BarcodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)
        setSupportActionBar(toolbar)
        val user = UserManager.getUser()

        var barcodeIV = findViewById<ImageView>(R.id.barcode_image_view)
        var multiFormatWriter = MultiFormatWriter()
        var bitMatrix = multiFormatWriter.encode(user.barcode, BarcodeFormat.CODABAR, 400, 400)
        var barcodeEncoder = BarcodeEncoder()
        barcodeIV.setImageBitmap(barcodeEncoder.createBitmap(bitMatrix))
    }
}
