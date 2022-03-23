package com.yxd.devlib.code.databinding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yxd.R
import com.yxd.databinding.ActivityDatabindingBinding

class DataBindingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDatabindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityDatabindingBinding>(
            this,
            R.layout.activity_databinding
        ).apply {
            // 以下两句代码等效
            tvTest.text = "Hello Kotlin DataBinding"
            textName = "Hello Kotlin DataBinding"
            // 以下两句代码等效
            btnTest.setOnClickListener {
                Toast.makeText(this@DataBindingActivity, "Hello btn click", Toast.LENGTH_SHORT)
                    .show()
            }
            btnClick = View.OnClickListener {
                Toast.makeText(this@DataBindingActivity, "Hello btn click", Toast.LENGTH_SHORT)
                    .show()
            }

            // 不要忘记初始化
            presenter = Presenter()

        }
    }

    // 方法引用
    inner class Presenter{
        fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            Log.d("Test", "dasdasdasdasdasda")
            binding.textName = s.toString()
        }

        fun clickButton(textNameStr:String){
            Toast.makeText(this@DataBindingActivity, textNameStr, Toast.LENGTH_SHORT).show()
        }
    }

}