package com.ttc.finch_station.data.base

import android.net.NetworkInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.ttc.finch_station.data.common.logDebug
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity() {
    var internetChecker: Disposable? = null

    override fun onResume() {
        super.onResume()
        //Used ReactiveNetwork to check realtime network connectivity information
        internetChecker = ReactiveNetwork
            .observeNetworkConnectivity(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connectivity: Connectivity ->
                if (connectivity.state() == NetworkInfo.State.CONNECTED) {
                    onInternetConnect()
                } else {
                    onInternetDisconnect()
                }
            }
    }



    override fun onPause() {
        super.onPause()
        internetChecker?.dispose()
        logDebug {}
    }





    abstract fun onInternetConnect()
    abstract fun onInternetDisconnect()


}