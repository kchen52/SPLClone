package libraryapp.kchen52.com.surreypubliclibraryclone.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import libraryapp.kchen52.com.surreypubliclibraryclone.HTTPRequest
import libraryapp.kchen52.com.surreypubliclibraryclone.OnFinishListener
import libraryapp.kchen52.com.surreypubliclibraryclone.R
import libraryapp.kchen52.com.surreypubliclibraryclone.user.User
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager
import okhttp3.Response

class LoginFragment : Fragment() {

    private val loginUrl = "https://surrey.bibliocommons.com/api/Library/71/logIn"
    private lateinit var usernameTV : TextView
    private lateinit var passwordTV : TextView
    private lateinit var loginButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usernameTV = view.findViewById(R.id.username_edit_text)
        passwordTV = view.findViewById(R.id.password_edit_text)

        loginButton = view.findViewById(R.id.login_button)

        var onFinishListener = object : OnFinishListener {
            override fun onFinish(response: Response) {
                if (response.body() != null) {
                    val userManager = UserManager
                    var user = User(response.body()!!.string())
                    userManager.setUser(user)

                    val ft = activity!!.supportFragmentManager.beginTransaction()
                    ft.replace(R.id.upper_fragment, MyFragmentManager.getUserFragment())
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ft.commit()
                }
                // Parse then data, and refresh the page
            }

            override fun onFailure(exception: Exception) {
                Toast.makeText(activity!!.applicationContext, exception.message, Toast.LENGTH_LONG).show()
            }
        }

        loginButton.setOnClickListener({
            if (usernameTV.text.toString().isBlank() || passwordTV.text.toString().isBlank()) {
                Toast.makeText(activity!!.applicationContext, "Username or password cannot be blank.", Toast.LENGTH_LONG).show()
            } else {
                var loadingDialog = LoadingDialog()
                loadingDialog.show(fragmentManager!!.beginTransaction(), "loading dialog")
                HTTPRequest().makeRequest("$loginUrl/${usernameTV.text}/${passwordTV.text}", onFinishListener, loadingDialog)
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }
}