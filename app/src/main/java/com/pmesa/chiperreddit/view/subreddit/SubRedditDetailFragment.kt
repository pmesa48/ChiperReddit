package com.pmesa.chiperreddit.view.subreddit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pmesa.chiperreddit.BuildConfig

import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import com.pmesa.chiperreddit.view.external.WebViewActivity
import com.pmesa.chiperreddit.viewmodel.SubRedditDetailViewModel
import com.pmesa.chiperreddit.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_sub_reddit.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SubRedditDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SubRedditDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubRedditDetailFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var mUrl: String? = null

    private var webViewUrl: String? = null

    private lateinit var viewModel: SubRedditDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mUrl = it.getString(ARG_PARAM_SUBREDDIT_URL)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        go_to_btn.setOnClickListener {
            activity?.let {
                val intent = Intent(it, WebViewActivity::class.java)
                if (webViewUrl != null && webViewUrl!!.isNotEmpty()) {
                    intent.putExtra(WebViewActivity.URL, webViewUrl)
                    startActivity(intent)
                }
            }
        }
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory.getInstance(activity!!.application)).get(SubRedditDetailViewModel::class.java)
        viewModel.getSubReddit(mUrl ?: "")?.observe(viewLifecycleOwner, Observer {
            displayData(it)
        })
    }

    private fun displayData(subreddit: RoomSubReddit?) {
        subreddit?.let {
            webViewUrl = "${BuildConfig.BASE_URL}${subreddit.url}"
            description_tv.text = it.description
            url_tv.text = it.url
            display_name_tv.text = it.displayName
            lang_tv.text = subreddit.lang?.toUpperCase()
            subscribers_tv.text = "${subreddit?.subscribers}"
            over18_tv.text = if(subreddit.over18 == true) "YES" else "NO"
            if(subreddit.icon != null && subreddit.icon.isNotBlank())
                Picasso.get().load(subreddit.icon).into(community_icon_iv)
        }
        activity?.title = "${subreddit?.displayName} Reddit"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_reddit, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {
        const val ARG_PARAM_SUBREDDIT_URL = "param1"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment SubRedditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            SubRedditDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_SUBREDDIT_URL, param1)
                }
            }
    }
}
