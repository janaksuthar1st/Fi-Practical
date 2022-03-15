package com.fi.practicaljanak.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.fi.practicaljanak.R
import com.fi.practicaljanak.common.Resource
import com.fi.practicaljanak.repository.network.FiNetworkRepository
import com.fi.practicaljanak.utils.CommonUtils.checkInternetConnected

class HomeScreenViewModel(
    application: Application,
    private val fiNetworkRepository: FiNetworkRepository
) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val mContext = application.applicationContext

    val fetchAppAssetsResponse: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun fetchAppAssetsByPackage(packageName: String) {
        if (!mContext.checkInternetConnected()) {
            fetchAppAssetsResponse.value =
                Resource.NoInternetError<String>(mContext.getString(R.string.default_internet_message))
        } else {
            fetchAppAssetsResponse.addSource(
                fiNetworkRepository.callGetAppsAssetsByPackage(packageName)
            ) {
                fetchAppAssetsResponse.value = it
            }
        }
    }
}