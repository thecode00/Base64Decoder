package com.thecode.base64decoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thecode.base64decoder.databinding.ActivityMainBinding
import com.thecode.base64decoder.databinding.ActivityPrivacyBinding

class PrivacyActivity : AppCompatActivity() {
    private var mBinding: ActivityPrivacyBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}