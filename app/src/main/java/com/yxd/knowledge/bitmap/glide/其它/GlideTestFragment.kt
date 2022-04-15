package com.yxd.knowledge.bitmap.glide.其它

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yxd.devlib.base.TestFragment

class GlideTestFragment: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        Glide.with(requireActivity())
            .load("https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white-d0c9fe2af5.png")
            .into(ImageView(requireContext()))
    }
}