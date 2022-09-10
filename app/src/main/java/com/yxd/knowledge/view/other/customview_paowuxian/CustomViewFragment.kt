package com.yxd.knowledge.view.other.customview_paowuxian

import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment

class CustomViewFragment: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        addView(DrawBasicView(requireContext()), 200, 200)
    }
}