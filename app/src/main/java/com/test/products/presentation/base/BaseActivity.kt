package com.test.products.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
 import androidx.viewbinding.ViewBinding

open abstract class BaseActivity< viewbind : ViewBinding>():AppCompatActivity() {
    public lateinit var binding: viewbind

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = setBindingView()
        setContentView(binding.root)


    }


    abstract fun setBindingView():viewbind
}