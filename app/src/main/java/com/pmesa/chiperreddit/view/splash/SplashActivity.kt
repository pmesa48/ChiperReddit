package com.pmesa.chiperreddit.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pmesa.chiperreddit.view.list.SubRedditListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, SubRedditListActivity::class.java))
            finish()
        }, 2000)
    }
}
