package com.pmesa.chiperreddit.view.subreddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pmesa.chiperreddit.R

class SubRedditDetailActivity : AppCompatActivity(), SubRedditDetailFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_reddit)
        supportFragmentManager.beginTransaction()
            .replace(R.id.subreddit_fragment_container,
                SubRedditDetailFragment.newInstance(intent.getStringExtra(SubRedditDetailFragment.ARG_PARAM_SUBREDDIT_URL) ?: ""))
            .commitNowAllowingStateLoss()
    }
}
