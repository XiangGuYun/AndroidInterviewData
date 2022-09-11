package com.yxd.knowledge.design.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yxd.MvvmFragmentBinding

class MvvmFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 获取binding
        val binding = MvvmFragmentBinding.inflate(inflater, container, false)
        // 创建一个Model
        val user = User("消失的光芒", "123456")
        // 设置Model
        binding.user = user
        binding.btnChange.setOnClickListener {
            // 改变Model
            user.name = "未来"
        }
        binding.btnChangeVisibility.setOnClickListener {
            user.isHide = !user.isHide
        }
        // 返回与绑定关联的布局文件中最外层的视图。
        return binding.root
    }
}