package libraryapp.kchen52.com.surreypubliclibraryclone

import android.content.SharedPreferences

class SharedPreferenceManager(private var sharedPreferences: SharedPreferences) {
    fun getPreference(key : String) {
        sharedPreferences.getString(key, "")
    }

    fun setPreference(key: String, value: String) {
        var sharedPreferenceEditor = sharedPreferences.edit()
        sharedPreferenceEditor.putString(key, value)
    }
}