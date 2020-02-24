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

    private var viewModel: ConnectivityViewModel? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory
            .getInstance(activity!!.application)).get(ConnectivityViewModel::class.java)
        viewModel?.getStatus()?.observe(viewLifecycleOwner, Observer {
            draw(it)
        })
    }

    private fun draw(status: Status?) {
        if(status != null){
            if(status.connected){
                makeAvailable()
            } else {
                makeUnavailable()
            }
        } else {
            makeUnavailable()
        }
    }

    fun makeUnavailable() {
        connectivity_container.visibility = View.VISIBLE
        connectivity_container.setBackgroundResource(R.color.colorAccent)
        connectivity_status_tv.text = "Offline"
    }

    fun makeAvailable() {
        connectivity_container.visibility = View.VISIBLE
        connectivity_container.setBackgroundResource(R.color.colorPrimaryDark)
        connectivity_status_tv.text = "Online"
        Handler().postDelayed({ connectivity_container.visibility = View.GONE }, 2000)
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnConnectivityEvent

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectivityFragment.
         */
        @JvmStatic
        fun newInstance() = ConnectivityFragment().apply { arguments = Bundle() }
    }
}
