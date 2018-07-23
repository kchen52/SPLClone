package libraryapp.kchen52.com.surreypubliclibraryclone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.widget.*
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.MyFragmentManager
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializes the default fragment, which is always the CatalogFragment
        // TODO: Make this config-able based on user settings
        setDefaultFragment()

        val catalogLinearLayout = findViewById<LinearLayout>(R.id.catalog_linear_layout)
        val libraryLinearLayout = findViewById<LinearLayout>(R.id.library_linear_layout)
        val profileLinearLayout = findViewById<LinearLayout>(R.id.profile_linear_layout)
        val settingsLinearLayout = findViewById<LinearLayout>(R.id.settings_linear_layout)

        // TODO: Persist fragments so we don't unnecessarily recreate them

        catalogLinearLayout.setOnClickListener({
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.upper_fragment, MyFragmentManager.getCatalogFragment())
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.commit()
        })

        profileLinearLayout.setOnClickListener({
            val ft = supportFragmentManager.beginTransaction()
            if (UserManager.isUserAuthenticated()) {
                ft.replace(R.id.upper_fragment, MyFragmentManager.getUserFragment())
            } else {
                ft.replace(R.id.upper_fragment, MyFragmentManager.getLoginFragment())
            }
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.commit()
        })
    }

    private fun setDefaultFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.upper_fragment, MyFragmentManager.getCatalogFragment())
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }
}

