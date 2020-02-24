package com.pmesa.chiperreddit.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.view.connectivity.ConnectivityActivity
import com.pmesa.chiperreddit.view.connectivity.ConnectivityFragment

class MainActivity : ConnectivityActivity(), ConnectivityFragment.OnConnectivityEvent {

    private lateinit var connectivityFragment: ConnectivityFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectivityFragment = ConnectivityFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment_container, SubRedditListFragment.newInstance())
            .replace(R.id.connectivity_fragment_container, connectivityFragment)
            .commitNowAllowingStateLoss()
    }

    override fun networkAvailable() {
        super.networkAvailable()
        Handler().postDelayed({ connectivityFragment.makeAvailable() },1000)
    }

    override fun networkUnavailable() {
        super.networkUnavailable()
        Handler().postDelayed({ connectivityFragment.makeUnavailable() },1000)
    }
}
