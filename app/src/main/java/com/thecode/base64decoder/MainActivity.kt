package com.thecode.base64decoder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.thecode.base64decoder.databinding.ActivityMainBinding
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    var list_of_char = arrayOf("UTF_8", "ASC_II", "ISO_8859-1")
    var listc = arrayOf(Charsets.UTF_8, Charsets.US_ASCII, Charsets.ISO_8859_1)
    var charType = Charsets.UTF_8


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDecode.setOnClickListener {
            decode(binding.etDecode.text.toString())
        }
        binding.btnEncode.setOnClickListener {
            encode(binding.etDecode.text.toString())
        }
        binding.btnCopy.setOnClickListener {
            copy()
        }
        binding.spinChar.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_of_char)
        binding.spinChar.setSelection(0)
        binding.spinChar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                charType = listc[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val privacyIntent = Intent(this, PrivacyActivity::class.java)
        when (item.itemId){
            R.id.privacy_menu ->
                startActivity(privacyIntent)

        }
        return super.onOptionsItemSelected(item)
    }

    fun encode(str: String) {
        try {
            Log.e("chartype", charType.toString())
            binding.etDecoderesult.setText(Base64.encodeToString(str.toByteArray(charType), 0))
        } catch (e: Exception) {
            binding.etDecoderesult.setText("Invalid Code")
        }

    }

    private fun decode(str: String) {
        try {
            binding.etDecoderesult.setText(String(Base64.decode(str, 0), charType))
            Log.e("chartype", charType.toString())
        } catch (e: Exception) {
            binding.etDecoderesult.setText("Invalid Code")
        }

    }

    private fun copy() {
        val clip = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val text = binding.etDecoderesult.text.toString()
        if (text.isEmpty()) {
            return
        }
        val clipData: ClipData = ClipData.newPlainText("resultCopy", text)
        clip.setPrimaryClip(clipData)
        Toast.makeText(this, "Copy to Clipboard", Toast.LENGTH_SHORT).show()
    }


}