package com.yxd.knowledge.bitmap.myglide

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yxd.R
import com.yxd.devlib.base.TestFragment

class MyGlideFragment: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        val iv1 = addImageView(width = MP, height = 200.dp)
        val iv2 = addImageView(width = MP, height = 200.dp)
        val iv3 = addImageView(width = MP, height = 200.dp)
        addButton("显示网图"){
            MyGlide.with(requireContext())
                .load("https://www.baidu.com/img/flexible/logo/pc/result@2.png")
                .placeHolder(R.mipmap.ic_launcher)
                .listener {
                    Log.d("Test", "下载了图片")
                }.into(iv1)

            MyGlide.with(requireContext())
                .load("https://www.baidu.com/img/flexible/logo/pc/result@2.png")
                .placeHolder(R.mipmap.ic_launcher)
                .listener {
                    Log.d("Test", "下载了图片")
                }.into(iv2)

            MyGlide.with(requireContext())
                .load("https://www.baidu.com/img/flexible/logo/pc/result@2.png")
                .placeHolder(R.mipmap.ic_launcher)
                .listener {
                    Log.d("Test", "下载了图片")
                }.into(iv3)
        }
    }
}