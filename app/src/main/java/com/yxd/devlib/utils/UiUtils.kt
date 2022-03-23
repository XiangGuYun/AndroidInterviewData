package com.yxd.devlib.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable

object UiUtils {
    @JvmStatic
    fun px2dip(context: Context, pxValue: Number): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue.toFloat() / scale + 0.5f).toInt()
    }

    @JvmStatic
    fun dp2px(context: Context, dipValue: Number): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue.toFloat() * scale + 0.5f).toInt()
    }

    @JvmStatic
    fun px2sp(context: Context, pxValue: Number): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue.toFloat() / fontScale + 0.5f).toInt()
    }

    @JvmStatic
    fun sp2px(context: Context, spValue: Number): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue.toFloat() * fontScale + 0.5f).toInt()
    }

    /**
     * 获取矩形或圆角矩形drawable
     */
    fun getRectangleDrawable(
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