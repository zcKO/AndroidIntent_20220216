package com.jc.androidintent_20220216

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CALL_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()

        btnDial.setOnClickListener {
            val inputPhoneNum = edtPhoneNum.text.toString()

//            Intent 를 만들 때, 어디로 전화를 걸지 알려주어야 한다. Uri 를 이용해서 알려준다.
            val myUri = Uri.parse("tel:${inputPhoneNum}") // 띄워쓰기를 사용하면 안된다. (앱이 죽음)

            val myIntent = Intent(Intent.ACTION_DIAL, myUri)

            startActivity(myIntent)

        }

        btnCall.setOnClickListener {

            val inputPhone = edtPhoneNum.text.toString()
            val myUri = Uri.parse("tel:${inputPhone}")

            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)

        }

        btnSendTo.setOnClickListener {

//            문자 보낼 내용 추출
            val inputContent = edtContent.text.toString()

            val inputPhone = edtPhoneNum.text.toString()
            val myUri = Uri.parse("smsto:${inputPhone}")

            val myIntent = Intent(Intent.ACTION_SENDTO, myUri)

//            문자앱은 우리가 컨트롤 할 수 없다. 문자앱에서 정해둔 이름표를 찾아서 보내야한다.
            myIntent.putExtra("sms_body", inputContent)

            startActivity(myIntent)
        }

        btnNaverWebLink.setOnClickListener {

            val myUri = Uri.parse("https://www.naver.com")
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
            startActivity(myIntent)

        }

    }

    private fun setupPermissions() {
        // CALL_PHONE 퍼미션을 permission 변수에 담는다
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)

        // 권한이 없다면 makeRequest() 로 이동
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        // 권한 목록 담기
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한이 거부됨", Toast.LENGTH_SHORT).show()
        }

    }

}