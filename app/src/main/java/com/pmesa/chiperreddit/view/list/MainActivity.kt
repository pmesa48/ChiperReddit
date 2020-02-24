package com.pmesa.chiperreddit.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pmesa.chiperreddit.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.list_fragment_container,
            SubRedditListFragment.newInstance()
        ).commitNowAllowingStateLoss()
    }
}
