package com.tencent.qqmusic.api.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.tencent.qqmusic.api.demo.openid.OpenIDHelper
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class CallbackActivity : Activity() {
    companion object {
        val TAG = "CallbackActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_callback)
        getSchemeInfos()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        finish()
    }


    override fun onResume() {
        super.onResume()
        //getSchemeInfos()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        finish()
    }

    private fun getSchemeInfos() {
        // 获取uri参数
        val intent = intent
        val scheme = intent.scheme
        val uri = intent.data
        Log.i(CallbackActivity.TAG, "uri:" + uri)
        if (uri != null) {
            val cmd = uri.getQueryParameter("cmd")
            if (cmd == "open") {
                Log.i(TAG, "ret:" + uri.getQueryParameter("ret"))
            } else if (cmd == "verify") {
                val ret = uri.getQueryParameter("ret")
                var intent = Intent()
                intent.action = "callback_verify_notify"
                intent.putExtra("ret", ret)
                sendBroadcast(intent)
            } else {
                val loginResult = uri.getQueryParameter("qmlogin")
                if (loginResult == "1") {
                    Toast.makeText(this, "QQMusic login success!!!", Toast.LENGTH_LONG).show()
                } else if (loginResult == "0") {
                    Toast.makeText(this, "QQMusic login error!!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}
