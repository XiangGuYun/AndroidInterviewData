package com.yxd.knowledge.component.activity.codes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yxd.R

/**
 * Activity跳转生命周期总结
 * 
 * 1. A 跳转 B
 *  A onPause
 *  B onCreate
 *  B onStart
 *  B onResume
 *  A onStop
 *
 * 2. B 返回 A
 *  B onPause
 *  A onRestart
 *  A onStart
 *  A onResume
 *  B onStop
 *  B onDestroy
 *
 * 3. A 跳转 C（透明或对话框主题）
 *  A onPause
 *  C onCreate
 *  C onStart
 *  C onResume
 * 
 * 4. C 返回 A
 *  C onPause
 *  A onResume
 *  C onStop
 *  C onDestroy
 * 
 * 5. 显示A时按下Home键后A的生命周期变化
 *  A onPause
 *  A onStop
 *
 * 6. 横竖屏切换时的生命周期变化
 *  A onPause
 *  A onStop
 *  A onDestroy
 *  A onCreate
 *  A onStart
 *  A onResume
 * 
 * 7. 当启动模式为singleTop或singleTask或singleInstance时，A跳转A
 *  A onPause
 *  onNewIntent
 *  A onResume
 *
 * 8. 当启动模式为standard，A跳转A
 *  A onPause
 *  A onCreate
 *  A onStart
 *  A onResume
 *  A（上一个） onStop
 *
 * 9. 当启动模式为standard，A再返回A
 *  A（第二个） onPause
 *  A（第一个） onRestart
 *  A（第一个） onStart
 *  A（第一个） onResume
 *  A（第二个） onStop
 *  A（第二个） onDestroy
 */
class A : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aactivity)
        Log.d("Test", " A onCreate")
        findViewById<View>(R.id.btnTest).setOnClickListener {
            startActivity(Intent(this, BActivity::class.java))
        }
        findViewById<View>(R.id.btnTest1).setOnClickListener {
            startActivity(Intent(this, C::class.java))
        }
        findViewById<View>(R.id.btnTest2).setOnClickListener {
            startActivity(Intent(this, A::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", " A onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", " A onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", " A onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", " A onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", " A onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Test", " A onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("Test", "onNewIntent")
    }

}