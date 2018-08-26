package libraryapp.kchen52.com.surreypubliclibraryclone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import libraryapp.kchen52.com.surreypubliclibraryclone.util.Util

class MainActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var viewPager : ViewPager

    private lateinit var previouslySelectedMenuItem : MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewpager)
        Util.setupViewPager(viewPager, supportFragmentManager, 0)

        previouslySelectedMenuItem = bottom_bar.menu.getItem(0)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffestPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                previouslySelectedMenuItem.isChecked = false
                bottom_bar.menu.getItem(position).isChecked = true
                previouslySelectedMenuItem = bottom_bar.menu.getItem(position)
            }
        })

        bottom_bar.setOnNavigationItemSelectedListener { item ->
            when {
                item.itemId == R.id.action_catalog -> viewPager.currentItem = 0
                item.itemId == R.id.action_library -> viewPager.currentItem = 1
                item.itemId == R.id.action_profile -> viewPager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
