package com.yxd.knowledge.view.other.customview_paowuxian

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawBasicView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED
    }

    private val paint1 = Paint().apply {
        color = Color.WHITE
    }

    /**
     * 绘制View的主要方法，注意不是唯一方法
     * @param canvas Canvas
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(100f, 100f, 100f, paint)
        // 保存当前图层，新建一个图层
        canvas?.save()
        // 对当前的图层可绘制区域进行裁剪
        canvas?.clipRect(50f, 50f, 150f, 150f)
        canvas?.drawRect(50f, 50f, 150f, 150f, paint1)
        // 合并图层
        canvas?.restore()
    }

}