package com.example.thenewyorktime.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.thenewyorktime.R
import com.example.thenewyorktime.presentation.dialog.LoadingFragment

fun FragmentActivity.showLoading() {
    this.supportFragmentManager.findFragmentByTag(LoadingFragment.TAG)?.let {
        this.supportFragmentManager.beginTransaction().show(it).commit()
    } ?: run {
        val loadingFragment = LoadingFragment.newInstance()
        this.supportFragmentManager.beginTransaction()
            .add(R.id.main_nav_host, loadingFragment, LoadingFragment.TAG)
            .commit()
    }
}

fun FragmentActivity.hideLoading() {
    this.supportFragmentManager.findFragmentByTag(LoadingFragment.TAG)?.let {
        this.supportFragmentManager.beginTransaction().hide(it).commit()
    }
}

fun Fragment.showLoading() {
    requireActivity().showLoading()
}

fun Fragment.hideLoading(){
    requireActivity().hideLoading()
}