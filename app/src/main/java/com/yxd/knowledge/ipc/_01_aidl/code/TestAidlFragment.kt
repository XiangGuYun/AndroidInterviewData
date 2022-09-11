package com.yxd.knowledge.ipc._01_aidl.code

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment

class TestAidlFragment : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        requireActivity().startService(Intent(requireContext(), AidlService::class.java))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().stopService(Intent(requireContext(), AidlService::class.java))
    }
}