package libraryapp.kchen52.com.surreypubliclibraryclone

import android.content.IntentSender
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.LoadingDialog
import okhttp3.*
import java.io.IOException

class HTTPRequest {

    // TODO: Implement headers, method, etc.
    // Makes a call given the URL passed as a parameter
     fun makeRequest(url : String, onFinishListener : OnFinishListener, loadingDialog: LoadingDialog) {
        var request = Request.Builder().url(url).build()

        var client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFinishListener.onFailure(e)
                loadingDialog.dismiss()
            }
            override fun onResponse(call: Call, response: Response) {
                onFinishListener.onFinish(response)
                loadingDialog.dismiss()
            }
        })
    }
}
