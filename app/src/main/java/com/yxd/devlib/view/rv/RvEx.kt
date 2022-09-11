package com.yxd.devlib.view.rv

import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.*

/**
 * RecyclerView扩展类
 *
 * @author YeXuDong
 */
interface RvEx {

    /**
     * 生成并设置适配器
     * @param data 数据集合
     * @param bindItemWithData 绑定数据
     * @param getLayoutIndex 决定列表项与列表项布局id的对应关系，为null是默认均采用第一个布局id
     * @param itemLayoutId 传入可变长度的布局id数组
     */
    fun <T, R : List<T>> RVUtils.generate(
        data: R,
        bindItemWithData: (h: EasyRVHolder, i: Int, item: T) -> Unit,
        getLayoutIndex: ((i: Int) -> Int)? = null,
        vararg itemLayoutId: Int
    ) {
        if (rv.layoutManager == null) {
            rv.layoutManager = LinearLayoutManager(context)
        }
        rv.adapter = object : EasyRVAdapter<T>(context, data, *itemLayoutId) {
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
    }

    /**
     * 设置带有HeaderView的适配器
     *
     * @receiver RVUtils
     * @param data List<T>
     * @param headerViewId Int
     * @param handleHeaderView (holder:Holder)->Unit
     * @param handleNormalView (holder:Holder,pos:Int)->Unit
     * @param handleNormalLayoutIndex (pos:Int)->Int
     * @param itemId IntArray
     */
    fun <T, R : List<T>> RVUtils.generateH(
        data: R,
        headerViewId: Int,
        handleHeaderView: (holder: EasyRVHolder) -> Unit,
        handleNormalView: (holder: EasyRVHolder, pos: Int, item: T) -> Unit,
        handleNormalLayoutIndex: (pos: Int) -> Int,
        vararg itemId: Int
    ) {
        needHeader = true
        generate(data, { holder, pos, item ->
            when (pos) {
                0 -> {
                    handleHeaderView.invoke(holder)
                }
                else -> {
                    handleNormalView.invoke(holder, pos, item)
                }
            }
        }, {
            when (it) {
                0 -> 0
                else -> handleNormalLayoutIndex.invoke(it) + 1
            }
        }, headerViewId, *itemId)
    }

    /**
     * 设置带有HeaderView和FooterView的适配器
     *
     * @receiver RVUtils
     * @param data List<T>
     * @param headerViewId Int
     * @param handleHeaderView (holder:Holder)->Unit
     * @param handleNormalView (holder:Holder,pos:Int)->Unit
     * @param handleNormalLayoutIndex (pos:Int)->Int
     * @param itemId IntArray
     */
    fun <T, R : List<T>> RVUtils.generateHF(
        data: R,
        headerViewId: Int,
        handleHeaderView: (headerHolder: EasyRVHolder) -> Unit,
        footerViewId: Int,
        handleFooterView: (footerHolder: EasyRVHolder) -> Unit,
        handleNormalView: (normalHolder: EasyRVHolder, pos: Int, item: T) -> Unit,
        handleNormalLayoutIndex: (pos: Int) -> Int,
        vararg itemId: Int
    ): RVUtils {
        needHeader = true
        needFooter = true
        generate(data, { holder, pos, item ->
            when (pos) {
                0 -> {
                    handleHeaderView.invoke(holder)
                }
                data.lastIndex -> {
                    handleFooterView.invoke(holder)
                }
                else -> {
                    handleNormalView.invoke(holder, pos, item)
                }
            }
        }, {
            when (it) {
                0 -> 0
                data.lastIndex -> 1
                else -> handleNormalLayoutIndex.invoke(it) + 2
            }
        }, headerViewId, footerViewId, *itemId)
        return this
    }

    /**
     * 遍历RecyclerView的子视图
     *
     * @receiver RecyclerView
     * @param fun1 (i:Int,it:View)->Unit
     */
    fun RecyclerView.foreachIndexed(fun1: (i: Int, it: View) -> Unit) {
        post {
            for (i in 0 until childCount) {
                fun1.invoke(i, getChildAt(i))
            }
        }
    }

    fun RecyclerView.foreach(fun1: (it: View) -> Unit) {
        post {
            for (i in 0 until childCount) {
                fun1.invoke(getChildAt(i))
            }
        }
    }

    /**
     * 简化ViewHolder的view获取，无需指定泛型
     */
    fun EasyRVHolder.v(id: Int): View {
        return getView(id)
    }

    fun EasyRVHolder.vNull(id: Int): View? {
        return getView(id)
    }

    /**
     * 简化ViewHolder的view获取，需要指定泛型
     */
    fun <T : View> EasyRVHolder.view(id: Int): T {
        return getView(id)
    }

    /**
     * 简化ViewHolder的ImageView获取
     */
    fun EasyRVHolder.iv(id: Int): ImageView {
        return getView(id)
    }

    /**
     * 简化ViewHolder的TextView获取
     */
    fun EasyRVHolder.tv(id: Int): TextView {
        return getView(id)
    }

    fun EasyRVHolder.tvNull(id: Int): TextView? {
        return getView(id)
    }

    /**
     * 简化ViewHolder的RecyclerView获取
     */
    fun EasyRVHolder.rv(id: Int): RecyclerView {
        return getView(id)
    }

    /**
     * 简化ViewHolder的EditText获取
     */
    fun EasyRVHolder.et(id: Int): EditText {
        return getView(id)
    }

    /**
     *  简化setImageResource，且返回Holder
     */
    fun EasyRVHolder.ir(ivId: Int, imgId: Int): EasyRVHolder {
        setImageResource(ivId, imgId)
        return this
    }

    /**
     *  简化setText，且返回Holder
     */
    fun EasyRVHolder.txt(id: Int, text: String?): EasyRVHolder {
        if (text.isNullOrEmpty()) {
            setText(id, "")
        } else {
            setText(id, text)
        }

        return this
    }

    /**
     *  简化setTextColor，且返回Holder
     */
    fun EasyRVHolder.color(id: Int, color: Int): EasyRVHolder {
        setTextColor(id, color)
        return this
    }

    /**
     *  简化setOnClickListener，且返回Holder
     */
    fun EasyRVHolder.click(id: Int, onClick: (View) -> Unit): EasyRVHolder {
        v(id).setOnClickListener(onClick)
        return this
    }


    /**
     *  简化ItemView的setOnLongClickListener，且返回Holder
     */
    fun EasyRVHolder.itemLongClick(click: (view: View) -> Unit): EasyRVHolder {
        itemView.setOnLongClickListener {
            click.invoke(it)
            return@setOnLongClickListener true
        }
        return this
    }

    /**
     *  简化设置Html格式文本，且返回Holder
     */
    fun EasyRVHolder.htmlText(id: Int, html: String): EasyRVHolder {
        getView<TextView>(id).text = Html.fromHtml(html)
        return this
    }

    /**
     * 添加分割线
     */
    fun RVUtils.decorate(drawableId: Int, isVertical: Boolean = true): RVUtils {
        val divider = DividerItemDecoration(
            context,
            if (isVertical) DividerItemDecoration.VERTICAL else DividerItemDecoration.HORIZONTAL
        )
        divider.setDrawable(context.resources.getDrawable(drawableId))
        rv.addItemDecoration(divider)
        return this
    }

    fun RVUtils.decorate(isVertical: Boolean = true): RVUtils {
        rv.addItemDecoration(
            DividerItemDecoration(
                context,
                if (isVertical) DividerItemDecoration.VERTICAL else DividerItemDecoration.HORIZONTAL
            )
        )
        return this
    }

    fun RVUtils.decorate(decoration: RecyclerView.ItemDecoration): RVUtils {
        rv.addItemDecoration(decoration)
        return this
    }

    /**
     * 设置线性吸附
     */
    fun RVUtils.snapLinear(): RVUtils {
        val helper = LinearSnapHelper()
        helper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置页面吸附
     */
    fun RVUtils.snapPager(): RVUtils {
        pagerHelper = PagerSnapHelper()
        pagerHelper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置自定义吸附
     */
    fun RVUtils.customSnap(set: (rv: RecyclerView) -> Unit): RVUtils {
        set.invoke(rv)
        return this
    }

    fun RVUtils.customSnap(snapHelper: SnapHelper): RVUtils {
        snapHelper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置Item增删动画
     *
     * Cool
     * LandingAnimator
     *
     * Scale
     * ScaleInAnimator, ScaleInTopAnimator, ScaleInBottomAnimator
     * ScaleInLeftAnimator, ScaleInRightAnimator
     *
     * Fade
     * FadeInAnimator, FadeInDownAnimator, FadeInUpAnimator
     * FadeInLeftAnimator, FadeInRightAnimator
     *
     * Flip
     * FlipInTopXAnimator, FlipInBottomXAnimator
     * FlipInLeftYAnimator, FlipInRightYAnimator
     *
     * Slide
     * SlideInLeftAnimator, SlideInRightAnimator, OvershootInLeftAnimator, OvershootInRightAnimator
     * SlideInUpAnimator, SlideInDownAnimator
     */
    fun <T : RecyclerView.ItemAnimator> RVUtils.anim(anim: T?): RVUtils {
        if (anim == null) {
            rv.itemAnimator = DefaultItemAnimator()
        } else {
            rv.itemAnimator = anim
        }
        return this
    }

    /**
     * 滚动到指定位置，指定位置会完整地出现在屏幕的最下方
     */
    fun <T> RecyclerView.scrollTo(position: Int, list: List<T>) {
        if (position >= 0 && position <= list.size - 1) {
            val firstItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val lastItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
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

    /**
     * 删除动画
     */
    fun <T> RecyclerView.deleteAnim(pos: Int, list: MutableList<T>) {
        list.removeAt(pos)
        adapter?.notifyItemRemoved(pos)
        adapter?.notifyItemRangeChanged(pos, list.size - pos)
    }

    /**
     * 获取RV的布局管理器
     * @receiver RecyclerView
     * @return T
     */
    fun <T : RecyclerView.LayoutManager> RecyclerView.lm(): T {
        return layoutManager as T
    }

    /**
     * 监听RV的滚动事件
     * @receiver RecyclerView
     * @param callback Function2<[@kotlin.ParameterName] Int, [@kotlin.ParameterName] Int, Unit>
     */
    fun RecyclerView.onScroll(callback: (dx: Int, dy: Int) -> Unit) {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                callback.invoke(dx, dy)
            }
        })
    }

    /**
     * 获取RVUtils的实例
     */
    val RecyclerView?.wrap: RVUtils get() = RVUtils(this)

    /**
     * 刷新RecyclerView
     * @receiver RecyclerView
     */
    fun RecyclerView.update() {
        this.adapter?.notifyDataSetChanged()
    }

    /**
     * 根据item总数和每页item数量计算总页数
     *
     * @param count Int    item总数
     * @param pageSize Int 每页的item数量
     * @return Int         总页数
     */
    fun calcTotalPage(count: Int?, pageSize: Int): Int {
        val countSafe = count ?: pageSize
        val firstNum = countSafe.div(pageSize.toDouble())
        val secondNum = firstNum.toInt()
        return if(firstNum != secondNum.toDouble()){
            secondNum + 1
        } else {
            secondNum
        }
    }

}