package com.pmesa.chiperreddit.view.connectivity

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.common.debug
import com.pmesa.chiperreddit.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_connectivity.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ConnectivityFragment.OnConnectivityEvent] interface
 * to handle interaction events.
 * Use the [ConnectivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectivityFragment : Fragment() {

    private var listener: OnConnectivityEvent? = null

    private var isAvailable: Boolean = false

    fun makeUnavailable() {
        isAvailable = false
        connectivity_container.visibility = View.VISIBLE
        connectivity_container.setBackgroundResource(R.color.colorAccent)
        connectivity_status_tv.text = getString(R.string.tag_offline)
    }

    fun makeAvailable() {
        if(!isAvailable) {
            isAvailable = true
            connectivity_container.visibility = View.VISIBLE
            connectivity_container.setBackgroundResource(R.color.colorPrimaryDark)
            connectivity_status_tv.text = getString(R.string.tag_online)
            Handler().postDelayed({ connectivity_container.visibility = View.GONE }, 2000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connectivity, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnConnectivityEvent) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnConnectivityEvent

    companion object {
        @JvmStatic
        fun newInstance() = ConnectivityFragment().apply { arguments = Bundle() }
    }
}
