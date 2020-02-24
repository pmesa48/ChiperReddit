package com.pmesa.chiperreddit.view.connectivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ConnectivityViewModel(private val manager: ChiperConnectivityManager, application: Application) : AndroidViewModel(application) {

    private var status: MutableLiveData<Status>? = null

    fun getStatus() : MutableLiveData<Status> {
        if(status == null){
            status = MutableLiveData()
            loadStatus()
        }
        return status!!
    }

    fun loadStatus() {
        manager.getStatus{ status?.value = it }
    }

}
