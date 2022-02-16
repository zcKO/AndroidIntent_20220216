package com.jc.androidintent_20220216

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDial.setOnClickListener {
            val inputPhoneNum = edtPhoneNum.text.toString()

//            Intent 를 만들 때, 어디로 전화를 걸지 알려주어야 한다. Uri 를 이용해서 알려준다.
            val myUri = Uri.parse("tel:${inputPhoneNum}") // 띄워쓰기를 사용하면 안된다. (앱이 죽음)

            val myIntent = Intent(Intent.ACTION_DIAL, myUri)

            startActivity(myIntent)

        }

    }
}