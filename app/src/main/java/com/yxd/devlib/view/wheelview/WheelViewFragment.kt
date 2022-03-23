package com.yxd.devlib.view.wheelview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yxd.R
import com.yxd.devlib.base.TestFragment
import com.yxd.devlib.utils.UiUtils
import com.yxd.devlib.view.wheelview.adapter.WheelAdapter
import com.yxd.devlib.view.wheelview.view.WheelView
import java.text.SimpleDateFormat
import java.util.*

class WheelViewFragment : TestFragment() {

    companion object{
        const val NUM_ONE_DAY_MILLIS = 24 * 60 * 60 * 1000L
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        addButton("显示") {
            getDatePickDialog1(requireContext()){}.show()
        }
    }

    /**
     * 获取日期选择框，年月日不联动
     */
    fun getDatePickDialog1(
        context: Context,
        getSelectedDate: (String) -> Unit
    ): BottomSheetDialog {
        return create(context, R.layout.dialog_date_select1).apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            findViewById<View>(R.id.tvCancel)?.setOnClickListener {
                dismiss()
            }
            val wv = findViewById<WheelView>(R.id.wv)
            wv?.setTextXOffset(0)
            wv?.setItemsVisibleCount(5)
            wv?.setCyclic(false)
            val sdf = SimpleDateFormat(context.getString(R.string.sdf1), Locale.CHINA)
            val day1 = sdf.format(System.currentTimeMillis())
            val day2 = sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS)
            val day3 =
                sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS * 2)
            val day4 =
                sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS * 3)
            val day5 =
                sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS * 4)
            val day6 =
                sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS * 5)
            val day7 =
                sdf.format(System.currentTimeMillis() + NUM_ONE_DAY_MILLIS * 7)
            val list = listOf(day1, day2, day3, day4, day5, day6, day7)
            wv?.adapter = object : WheelAdapter<String> {
                override fun getItemsCount(): Int {
                    return list.size
                }

                override fun getItem(index: Int): String {
                    return list[index]
                }

                override fun indexOf(o: String?): Int {
                    return -1
                }
            }
            findViewById<View>(R.id.tvConfirm)?.setOnClickListener {
                getSelectedDate.invoke(list[wv?.currentItem ?: 0])
                dismiss()
            }
        }
    }

    /**
     * 创建底部弹窗
     * @param ctx Context
     * @param dialogViewId Int
     * @return BottomSheetDialog
     */
    private fun create(
        ctx: Context,
        dialogViewId: Int,
        dialogBgColor: Int = Color.WHITE
    ): BottomSheetDialog {
        val dialog = BottomSheetDialog(ctx)
        val dialogView = LayoutInflater.from(ctx).inflate(dialogViewId, null)
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(dialogBgColor)
        gradientDrawable.cornerRadii = floatArrayOf(
            UiUtils.dp2px(ctx, 8).toFloat(),
            UiUtils.dp2px(ctx, 8).toFloat(),
            UiUtils.dp2px(ctx, 8).toFloat(),
            UiUtils.dp2px(ctx, 8).toFloat(),
            0.toFloat(),
            0.toFloat(),
            0.toFloat(),
            0.toFloat()
        )
        dialog.setContentView(dialogView)
        if (dialogBgColor == Color.TRANSPARENT) {
            dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        } else {
            dialogView.background = gradientDrawable
        }
        dialog.window?.setDimAmount(0.toFloat())
        return dialog
    }
}