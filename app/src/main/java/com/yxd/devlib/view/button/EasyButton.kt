package com.yxd.devlib.view.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import com.yxd.R

/**
 * 扁平式自定义按钮
 *
 * @author YeXuDong
 */
class EasyButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyleAttr) {

    /**
     * 边框颜色
     */
    private var mStrokeColor: Int

    /**
     * 边框宽度
     */
    private var mStrokeWidth: Float

    /**
     * 圆角半径
     */
    private var mCornerRadius: Float

    /**
     * 按钮按下时背景色
     */
    private var mBgPressedColor: Int

    /**
     * 按钮未按下时背景色
     */
    private var mBgCommonColor: Int

    /**
     * 按下时背景
     */
    private var mBgPressed: GradientDrawable

    /**
     * 未按下时背景
     */
    private var mBgUnPressed: GradientDrawable

    @SuppressLint("CustomViewStyleable")
    private val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.EasyButton)

    init {
        mBgCommonColor = mTypedArray.getColor(R.styleable.EasyButton_bgCommonColor, resources.getColor(android.R.color.holo_green_dark))
        mBgPressedColor = mTypedArray.getColor(R.styleable.EasyButton_bgPressedColor, mBgCommonColor)
        mCornerRadius = mTypedArray.getDimension(R.styleable.EasyButton_cornerRadius, 0f)
        mStrokeWidth = mTypedArray.getDimension(R.styleable.EasyButton_strokeWidth, 0f)
        mStrokeColor = mTypedArray.getColor(R.styleable.EasyButton_strokeColor, Color.BLACK)

        gravity = Gravity.CENTER

        val select = StateListDrawable()

        setTextColor(Color.WHITE)

        mBgUnPressed = getRectangleDrawable(
            cornerRadius = mCornerRadius,
            solidColor = mBgCommonColor,
            strokeWidth = mStrokeWidth.toInt(),
            strokeColor = mStrokeColor
        )

        mBgPressed = getRectangleDrawable(
            mCornerRadius,
            mBgPressedColor
        )

        select.addState(intArrayOf(-android.R.attr.state_pressed), mBgUnPressed)
        select.addState(intArrayOf(android.R.attr.state_pressed), mBgPressed)

        background = select

        // 防止未设置点击事件前select变化背景色无效
        setOnClickListener {  }

        mTypedArray.recycle()
    }

    /**
     * 设置新的背景，如果某个属性为null，则取默认值
     *
     * @param cornerRadiusNew  Float?  圆角半径
     * @param solidColorNew    Int?    未按下的背景色
     * @param strokeWidthNew   Int?    边框宽度
     * @param strokeColorNew   Int?    边框颜色
     * @param pressedColorNew  Int?    按下后的背景色
     */
    fun setBg(
        cornerRadiusNew: Float? = null,
        solidColorNew: Int? = null,
        strokeWidthNew: Int? = null,
        strokeColorNew: Int? = null,
        pressedColorNew: Int? = null
    ) {
        val select = StateListDrawable()
        mBgUnPressed = getRectangleDrawable(
            cornerRadius = cornerRadiusNew ?: mCornerRadius,
            solidColor = solidColorNew ?: mBgCommonColor,
            strokeWidth = strokeWidthNew ?: mStrokeWidth.toInt(),
            strokeColor = strokeColorNew ?: mStrokeColor
        )

        mBgPressed = getRectangleDrawable(
            cornerRadiusNew ?: mCornerRadius,
            (pressedColorNew ?: solidColorNew) ?: mBgPressedColor
        )

        select.addState(intArrayOf(-android.R.attr.state_pressed), mBgUnPressed)
        select.addState(intArrayOf(android.R.attr.state_pressed), mBgPressed)

        background = select
    }

    /**
     * 获取矩形或圆角矩形drawable
     */
    private fun getRectangleDrawable(
        cornerRadius: Float = 0f,
        solidColor: Int = Color.TRANSPARENT,
        strokeWidth: Int = 0,
        strokeColor: Int = Color.TRANSPARENT,
        dashWidth: Float = -1f,
        dashGap: Float = -1f
    ): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(solidColor)
        if (dashWidth != -1f && dashGap != -1f) {
            gradientDrawable.setStroke(strokeWidth, strokeColor)
        } else {
            gradientDrawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
        }
        gradientDrawable.cornerRadius = cornerRadius
        return gradientDrawable
    }


}