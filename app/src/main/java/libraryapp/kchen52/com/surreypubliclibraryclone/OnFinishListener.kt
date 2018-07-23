package libraryapp.kchen52.com.surreypubliclibraryclone

import okhttp3.Response

interface OnFinishListener {
    fun onFinish(response : Response)
    fun onFailure(exception: Exception)
}
