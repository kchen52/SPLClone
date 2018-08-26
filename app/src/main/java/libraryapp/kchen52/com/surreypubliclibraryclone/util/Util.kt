package libraryapp.kchen52.com.surreypubliclibraryclone.util

import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import libraryapp.kchen52.com.surreypubliclibraryclone.HTTPRequest
import libraryapp.kchen52.com.surreypubliclibraryclone.OnFinishListener
import libraryapp.kchen52.com.surreypubliclibraryclone.adapter.ViewPagerAdapter
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.CatalogFragment
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.LoginFragment
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.MapFragment
import libraryapp.kchen52.com.surreypubliclibraryclone.fragment.UserFragment
import libraryapp.kchen52.com.surreypubliclibraryclone.user.UserManager

object Util {
    fun setupViewPager(viewPager : ViewPager, supportFragmentManager : FragmentManager, positionToGoTo : Int) {
        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(CatalogFragment(), "Catalog")
        viewPagerAdapter.addFragment(MapFragment(), "Libraries")
        if (UserManager.isUserAuthenticated()) {
            viewPagerAdapter.addFragment(UserFragment(), "Profile")
        } else {
            viewPagerAdapter.addFragment(LoginFragment(), "Login")
        }
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = positionToGoTo
    }

    fun checkIfTokenValid(token : String, onFinishListener: OnFinishListener) {
        var tokenVerificationURL = "https://surrey.bibliocommons.com/api/LoggedInAccount/" +
                token + "/SessionId"
        // If we get a 404 then the token is invalid
        HTTPRequest().makeRequest(tokenVerificationURL, onFinishListener, null)
    }
}

