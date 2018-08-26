package libraryapp.kchen52.com.surreypubliclibraryclone.fragment

import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import libraryapp.kchen52.com.surreypubliclibraryclone.HTTPRequest
import libraryapp.kchen52.com.surreypubliclibraryclone.OnFinishListener
import libraryapp.kchen52.com.surreypubliclibraryclone.R
import libraryapp.kchen52.com.surreypubliclibraryclone.exception.LoginFailedException
import libraryapp.kchen52.com.surreypubliclibraryclone.user.User
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager
import libraryapp.kchen52.com.surreypubliclibraryclone.util.Util
import okhttp3.Response

class LoginFragment : Fragment() {

    private val loginUrl = "https://surrey.bibliocommons.com/api/Library/71/logIn"
    private lateinit var usernameTV : TextInputEditText
    private lateinit var passwordTV : TextInputEditText
    private lateinit var loginButton : MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usernameTV = view.findViewById(R.id.username_edit_text)
        passwordTV = view.findViewById(R.id.password_edit_text)

        loginButton = view.findViewById(R.id.login_button)

        var onFinishListener = object : OnFinishListener {
            override fun onFinish(response: Response) {
                var responseBody = response.body()!!.string()
                if (responseBody != null && ! responseContainsException(responseBody)) {

                    val userManager = UserManager
                    var user = User(responseBody)
                    userManager.setUser(user)

                    // TODO: Persist the user as a SharedPref object
                    var onFinishListener = object : OnFinishListener {
                        override fun onFinish(response: Response) {
                            activity!!.runOnUiThread {
                                var viewPager = activity?.findViewById<ViewPager>(R.id.viewpager)
                                Util.setupViewPager(viewPager!!, fragmentManager!!, 2)
                            }
                        }
                        override fun onFailure(exception: Exception) {
                            // Something wrong happened
                            exception.printStackTrace()
                        }
                    }
                    Util.checkIfTokenValid(user.sessionId, onFinishListener)
                } else {
                    onFailure(LoginFailedException(responseBody))
                }
                // Parse then data, and refresh the page
            }

            override fun onFailure(exception: Exception) {
                activity?.runOnUiThread {
                    Toast.makeText(activity!!.applicationContext, exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        loginButton.setOnClickListener {
            if (usernameTV.text.toString().isBlank() || passwordTV.text.toString().isBlank()) {
                Toast.makeText(activity!!.applicationContext, "Username or password cannot be blank.", Toast.LENGTH_LONG).show()
            } else {
                var loadingDialog = LoadingDialog()
                loadingDialog.show(fragmentManager!!.beginTransaction(), "loading dialog")
                HTTPRequest().makeRequest("$loginUrl/${usernameTV.text}/${passwordTV.text}", onFinishListener, loadingDialog)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    // TODO: Make this WAAAAAY better
    private fun responseContainsException(response : String) : Boolean {
        return response.contains("Exception")
    }
}