package com.pmesa.chiperreddit.view.list

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.common.debug
import com.pmesa.chiperreddit.repo.source.api.SubReddit
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import com.pmesa.chiperreddit.view.subreddit.SubRedditActivity
import com.pmesa.chiperreddit.view.subreddit.SubRedditFragment
import com.pmesa.chiperreddit.viewmodel.SubRedditListViewModel
import com.pmesa.chiperreddit.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.sub_reddit_list_fragment.*

class SubRedditListFragment : Fragment() {

    private lateinit var viewModel: SubRedditListViewModel

    private lateinit var viewAdapter: SubRedditListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sub_reddit_list_fragment, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refresh_layout_srl.setOnRefreshListener {
            viewModel.loadSubReddits()
        }
        viewAdapter = SubRedditListAdapter { position: Int, subReddit: RoomSubReddit ->
            debug(TAG, "Clicked position #$position - ${subReddit.displayName}")
            goToSubReddit(subReddit)
        }
        rvSubreddits.adapter = viewAdapter
        rvSubreddits.layoutManager = LinearLayoutManager(context)
        rvSubreddits.setHasFixedSize(true)
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory.getInstance(activity!!.application)).get(SubRedditListViewModel::class.java)
        viewModel.getSubReddits().observe(viewLifecycleOwner, Observer {
            updateSubRedditList(it)
        })

    }

    private fun goToSubReddit(subReddit: RoomSubReddit) {
        startActivity(Intent(activity, SubRedditActivity::class.java).apply {
            putExtra(SubRedditFragment.ARG_PARAM_SUBREDDIT_URL, subReddit.url)
        })
    }

    private fun updateSubRedditList(subreddits: List<RoomSubReddit>) {
        refresh_layout_srl.isRefreshing = false
        viewAdapter.update(subreddits)
    }

    companion object {
        val TAG = SubRedditListFragment::class.java.simpleName
        fun newInstance() =
            SubRedditListFragment()

    }

}
