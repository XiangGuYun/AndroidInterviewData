package com.yxd.devlib.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yxd.R

object DialogUtils {

    /**
     * 创建底部弹窗
     * @param ctx Context
     * @param dialogViewId Int
     * @return BottomSheetDialog
     */
    fun create(
        ctx: Context,
        dialogViewId: Int,
        cornerRadius: Float = 8f,
        cancelable:Boolean = true
    ): BottomSheetDialog {
        val dialog = BottomSheetDialog(ctx, R.style.BottomSheetDialog)
        dialog.setCancelable(cancelable)
        dialog.setCanceledOnTouchOutside(cancelable)
        val dialogView = LayoutInflater.from(ctx).inflate(dialogViewId, null)
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(Color.RED)
        gradientDrawable.cornerRadii = floatArrayOf(
            UiUtils.dp2px(ctx, cornerRadius).toFloat(),
            UiUtils.dp2px(ctx, cornerRadius).toFloat(),
            UiUtils.dp2px(ctx, cornerRadius).toFloat(),
            UiUtils.dp2px(ctx, cornerRadius).toFloat(),
            0f,
            0f,
            0f,
            0f
        )
        dialogView.background = gradientDrawable
        dialog.setContentView(dialogView)
        // 隐藏灰色遮罩
//        dialog.window?.setDimAmount(0f)
        return dialog
    }
}