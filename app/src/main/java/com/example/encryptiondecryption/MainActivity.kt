package com.example.encryptiondecryption

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var cryptoManager: CryptoManager
    lateinit var et : EditText
    lateinit var encBtn : Button
    lateinit var decBtn : Button
    lateinit var txt : TextView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cryptoManager = CryptoManager()
        initialize()

        encBtn.setOnClickListener{
            val byte = et.text.toString().encodeToByteArray()
            val file = File(filesDir,"secret.txt")
            if(!file.exists()){
                file.createNewFile()
            }

            val fos = FileOutputStream(file)
            txt.text = CryptoManager().encrypt(byte,fos)
        }
        decBtn.setOnClickListener{
            val file = File(filesDir,"secret.txt")
            txt.text = CryptoManager().decrypt(
                FileInputStream(file)
            ).decodeToString()
        }
    }

    private fun initialize() {
        et = findViewById(R.id.enterText)
        encBtn = findViewById(R.id.encryptBtn)
        decBtn = findViewById(R.id.decryptBtn)
        txt = findViewById(R.id.textView)
    }
}