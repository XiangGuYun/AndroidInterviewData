package com.yxd.devlib.view.rv

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class EasyRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    RecyclerView(context, attrs, defStyleAttr) {

    /**
     * 生成并设置适配器
     * @param data 数据集合
     * @param bindItemWithData 绑定数据
     * @param getLayoutIndex 决定列表项与列表项布局id的对应关系，为null是默认均采用第一个布局id
     * @param itemLayoutId 传入可变长度的布局id数组
     */
    fun <T, R : List<T>> generate(
        data: R,
        bindItemWithData: (h: EasyRVHolder, i: Int, item: T) -> Unit,
        getLayoutIndex: ((i: Int) -> Int)? = null,
        vararg itemLayoutId: Int
    ) {
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context)
        }
        adapter = object : EasyRVAdapter<T>(context, data, *itemLayoutId) {
            override fun onBindData(viewHolder: EasyRVHolder?, position: Int, item: T) {
                if (viewHolder != null) {
                    bindItemWithData.invoke(viewHolder, position, item)
                }
            }

            override fun getLayoutIndex(position: Int, item: T): Int {
                return getLayoutIndex?.invoke(position) ?: 0
            }
        }
    }

    /**
     * 设置网格列表
     * @param spanCount  列数
     * @param isVertical 是否垂直
     * @return
     */
    fun gridManager(spanCount: Int, isVertical: Boolean = true): EasyRecyclerView {
        layoutManager = if (isVertical) {
            GridLayoutManager(context, spanCount)
        } else {
            GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false)
        }
        return this
    }

    /**
     * 移动到指定位置，
     * @param position  要跳转的位置
     * @param offset 偏移量
     */
    fun moveToPosition(position: Int, offset: Int = 0) {
        if (layoutManager is LinearLayoutManager) {
            (layoutManager as LinearLayoutManager).apply {
                scrollToPositionWithOffset(position, offset)
                stackFromEnd = true
            }
        }
    }

    /**
     * 移动到指定位置，
     * 根据当前RecyclerView的条目数量，可以有效地避免指针越界
     * @param position 要跳转的位置
     * https://www.cnblogs.com/weizhxa/p/8144078.html
     */
    fun moveToPosition1(position: Int) {
        if (layoutManager !is LinearLayoutManager) return
        val manager = layoutManager as LinearLayoutManager
        val firstItem = manager.findFirstVisibleItemPosition()
        val lastItem = manager.findLastVisibleItemPosition()
        when {
            position <= firstItem -> {
                scrollToPosition(position)
            }
            position <= lastItem -> {
                val top = getChildAt(position - firstItem).top
                scrollBy(0, top)
            }
            else -> {
                scrollToPosition(position)
            }
        }
    }

}