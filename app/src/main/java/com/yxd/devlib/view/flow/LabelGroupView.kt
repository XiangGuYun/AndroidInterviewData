package com.yxd.devlib.view.flow

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import com.yxd.R

/**
 * 标签组视图
 * @author YeXuDong
 */
@Suppress("unused")
class LabelGroupView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FlowLayout(context, attrs) {

    private val labels = mutableListOf<LabelTextView>()

    private var onLabelListChangeListener: OnLabelListChangeListener? = null

    interface OnLabelListChangeListener {
        fun labelListChange(labels: List<String>)
    }

    fun setOnLabelListChangeListener(onLabelListChangeListener: OnLabelListChangeListener) {
        this.onLabelListChangeListener = onLabelListChangeListener
    }

    /**
     * 设置数据源
     * @param list List<String>
     */
    fun setData(list: List<String?>, readOnly: Boolean = false) {
        labels.clear()
        removeAllViews()
        list.forEach {
            val tv = LabelTextView(context).apply {
                text = it
                if (readOnly) setReadOnly()
            }
            addView(tv)
            labels.add(tv)
        }
    }

    /**
     * 返回选中的标签文本
     * @return List<String>
     */
    fun getSelectedLabels(): List<String> {
        return labels.filter { it.isLabelSelected }.map { it.text.toString() }
    }

    /**
     * 设置在设置星级后需要选中的标签
     * @param texts List<String>
     */
    fun setLabelsSelect(texts: List<String>) {
        // 遍历所有标签
        labels.forEach {
            if (it.isLabelSelected) {
                it.setUnselected()
            }
        }
        labels.forEach {
            if (texts.contains(it.text)) {
                it.performClick()
            }
        }
    }

    /**
     * 自定义标签TextView
     */
    inner class LabelTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) :
        androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

        /**
         * 文本是否只读
         */
        private var readOnly = false

        /**
         * 是否被选中
         */
        var isLabelSelected = false

        /**
         * 设置标签为只读
         */
        fun setReadOnly() {
            readOnly = true
            setTextColor(resources.getColor(R.color.color_1C6BBA))
            textSize = 12f
            background = drawableReadOnly
            setOnClickListener(null)
        }

        private val drawableUnSelect = getRectangleDrawable(
            cornerRadius = dpToPx(3).toFloat(),
            strokeWidth = dpToPx(1),
            strokeColor = resources.getColor(R.color.color_EBEBEB),
        )

        private val drawableSelected = getRectangleDrawable(
            cornerRadius = dpToPx(3).toFloat(),
            strokeWidth = dpToPx(1),
            strokeColor = resources.getColor(R.color.color_1C6BBA),
        )

        private val drawableReadOnly = getRectangleDrawable(
            cornerRadius = dpToPx(3).toFloat(),
            solidColor = resources.getColor(R.color.color_401C6BBA),
        )

        fun setUnselected() {
            background = drawableUnSelect
            setTextColor(resources.getColor(R.color.color_707070))
            isLabelSelected = false
        }

        init {
            setPadding(
                dpToPx(6),
                dpToPx(4),
                dpToPx(6),
                dpToPx(4)
            )
            gravity = Gravity.CENTER
            if (!readOnly) {
                setTextColor(resources.getColor(R.color.color_707070))
                textSize = 14f
                background = drawableUnSelect
                setOnClickListener {
                    if (!isLabelSelected) {
                        background = drawableSelected
                        setTextColor(resources.getColor(R.color.color_1C6BBA))
                    } else {
                        background = drawableUnSelect
                        setTextColor(resources.getColor(R.color.color_707070))
                    }
                    isLabelSelected = !isLabelSelected
                    onLabelListChangeListener?.labelListChange(getSelectedLabels())
                }
            } else {
                setTextColor(resources.getColor(R.color.color_1C6BBA))
                textSize = 12f
                background = drawableReadOnly
            }
        }
    }

    init {
        childSpacing = dpToPx(8)
        rowSpacing = dpToPx(8).toFloat()
    }

    fun dpToPx(dipValue: Number): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue.toFloat() * scale + 5).toInt()
    }

    /**
     * 获取矩形或圆角矩形drawable
     */
    fun getRectangleDrawable(
        cornerRadius: Float = 0f,
        solidColor: Int = Color.TRANSPARENT,
        strokeWidth: Int = 0,
        strokeColor: Int = Color.TRANSPARENT,
        dashWidth: Float = 1f,
        dashGap: Float = 1f
    ): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(solidColor)
        if (dashWidth != 1f && dashGap != 1f) {
            gradientDrawable.setStroke(strokeWidth, strokeColor)
        } else {
            gradientDrawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
        }
        gradientDrawable.cornerRadius = cornerRadius
        return gradientDrawable
    }
}
