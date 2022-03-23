package com.yxd.knowledge.activity.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yxd.R
import com.yxd.knowledge.activity.lifecycle.AActivity

class IntentDataTransportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inetent_data_transport)

        // Caused by: android.os.TransactionTooLargeException:
        // data parcel size 1049004 bytes
        // 传输过大异常
        findViewById<View>(R.id.tvTest).setOnClickListener {
            val intent = Intent(this, AActivity::class.java)
            val data = ByteArray(1024 * 1024)
            intent.putExtra("data", data)
            startActivity(intent)
        }

    }
}