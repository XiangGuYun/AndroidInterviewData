package com.yxd.knowledge.view.面试题.other.rv
import com.yxd.devlib.base.StudyFragment
import com.yxd.devlib.base.TestFragment

class RvFragment : StudyFragment() {
    override fun getFragmentList(): List<TestFragment> {
        return listOf(垂直列表(), 横向列表())
    }
}