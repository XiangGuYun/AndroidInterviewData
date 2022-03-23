package com.yxd.devlib.view.vp

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yxd.devlib.view.rv.EasyRVAdapter
import com.yxd.devlib.view.rv.EasyRVHolder

interface Vp2Ex {

    /**
     * 设置当前可见页面的两边应该保留的页面数。超过此限制的页面将在需要时从适配器重新创建。
     *
     * ViewPager2默认就实现了懒加载。
     * 但是如果想避免Fragment频繁销毁和创建造成的开销，可以通过setOffscreenPageLimit()方法设置预加载数量，
     * 将数据加载逻辑放到Fragment的onResume()方法中。
     *
     * @param limit Int
     * @return ViewPager2
     */
    fun ViewPager2.setOffscreenPageLimit(limit: Int): ViewPager2 {
        offscreenPageLimit = limit
        return this
    }

    /**
     * 设置ViewPager2是否为垂直方向
     *
     * @param boolean Boolean
     * @return ViewPager2
     */
    fun ViewPager2.setVertical(boolean: Boolean = true): ViewPager2 {
        if (boolean) {
            this.orientation = ViewPager2.ORIENTATION_VERTICAL
        } else {
            this.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        return this
    }

    /**
     * 设置允许用户滑动ViewPager2
     *
     * @param boolean Boolean
     * @return ViewPager2
     */
    fun ViewPager2.setAllowUserInput(boolean: Boolean = true): ViewPager2 {
        this.isUserInputEnabled = boolean;
        return this
    }

    /**
     * 绑定1~N个View
     *
     * @param data R
     * @param bindItemWithData
     * @param getLayoutIndex
     * @param itemLayoutId
     * @return ViewPager2
     */
    fun <T, R : List<T>> ViewPager2.generate(
        data: R,
        bindItemWithData: (h: EasyRVHolder, i: Int, item: T) -> Unit,
        getLayoutIndex: ((i: Int) -> Int)? = null,
        vararg itemLayoutId: Int
    ): ViewPager2 {
        this.adapter = object : EasyRVAdapter<T>(context, data, *itemLayoutId) {
            override fun onBindData(
                viewHolder: EasyRVHolder,
                position: Int,
                item: T
            ) {
                bindItemWithData.invoke(viewHolder, position, item)
            }

            override fun getLayoutIndex(layoutIndex: Int, item: T): Int {
                return getLayoutIndex?.invoke(layoutIndex) ?: 0
            }
        }
        return this
    }

    /**
     * 绑定TabLayout，支持刷新（刷新需要先detach，再重新attach）
     * @receiver ViewPager2
     * @param tabs TabLayout
     * @param listener Function2<Tab, Int, Unit>
     */
    fun ViewPager2.bindTL(tabs: TabLayout, listener: (TabLayout.Tab, Int) -> Unit){
        val tlMediator = TabLayoutMediator(tabs, this){tab, i ->
            listener.invoke(tab, i)
        }
        tlMediator.attach()
    }

}