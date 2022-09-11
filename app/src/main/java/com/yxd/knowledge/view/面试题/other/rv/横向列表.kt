package com.yxd.knowledge.view.面试题.other.rv

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yxd.devlib.base.TestFragment

class 横向列表 : TestFragment() {

    override fun init(view: View, savedInstanceState: Bundle?) {

        addView(RecyclerView(requireContext()).apply {
            // 准备数据
            val list = (1..50).map { "列表项${it}" }.toList()

            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)

            adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): RecyclerView.ViewHolder {
                    return object : RecyclerView.ViewHolder(TextView(requireContext()).apply {
                        id = 1 // 给一个已经存在的id也可以，例如R.id.tvTest
                        layoutParams = RecyclerView.LayoutParams(100.dp, MP)
                        gravity = Gravity.CENTER
                        textSize = 20f
                    }) {}
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                    holder.itemView.findViewById<TextView>(1).text = list[position]
                }

                override fun getItemCount(): Int {
                    return list.size
                }

            }
        },  WC, MP)
    }
}