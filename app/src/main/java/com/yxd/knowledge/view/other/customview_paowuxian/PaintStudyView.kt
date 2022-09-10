package com.yxd.knowledge.view.other.customview_paowuxian

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ComposeShader
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RadialGradient
import android.graphics.Shader
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import com.yxd.R

class PaintStudyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    val paint = Paint().apply {
        // 学习Paint设置颜色的三种方法
        howToSetColor(this)
        // 学习使用ColorFilter来对颜色做二次处理
        howToSetColorFilter(this)
    }

    /**
     * 在 Paint 里设置 ColorFilter ，使用的是
     * Paint.setColorFilter(ColorFilter filter) 方法。
     * ColorFilter 并不直接使用，而是使用它的子类。
     * 它共有三个子类：
     * ① LightingColorFilter-模拟简单的光照效果
     * ② PorterDuffColorFilter
     * ③ ColorMatrixColorFilter
     * @param paint Paint
     */
    private fun howToSetColorFilter(paint: Paint) {
        // LightingColorFilter
        // 构造方法 LightingColorFilter(int mul, int add)
        // mul 和 add 都是和颜色值格式相同的 int 值，
        // 其中 mul 用来和目标像素相乘，add 用来和目标像素相加：
        // R' = R * mul.R / 0xff + add.R
        // G' = G * mul.G / 0xff + add.G
        // B' = B * mul.B / 0xff + add.B
    }

    private fun howToSetColor(paint: Paint) {
        paint.apply {
            // 方法1
            color = Color.RED
            // 方法2
            setARGB(255, 255, 0, 0)
            // 方法3 - Shader着色器
            // =========================
            // 简介：着色器设置的是一个颜色方案，或者说是一套着色规则。
            // 当设置了 Shader 之后，Paint 在绘制图形和文字时就再不使用
            // setColor/ARGB() 设置的颜色，而是使用 Shader 方案中的颜色。
            // -------------------------
            //  LinearGradient 线性渐变
            // -------------------------
            shader = LinearGradient(
                100f,// 渐变起点的X轴位置
                100f,// 渐变起点的Y轴位置
                500f,// 渐变终点的X轴位置
                500f,// 渐变终点的Y轴位置
                Color.RED,// 渐变起点的颜色
                Color.BLUE,// 渐变终点的颜色
                Shader.TileMode.CLAMP
                // 瓦片模式，用于指定如何填充其它区域的规则
                // ---------------------------------
                // CLAMP: 会在起点和终点之外延续端点处的颜色
                // MIRROR：镜像模式
                // REPEAT：重复模式
                // ---------------------------------
            )
            // -------------------------
            // RadialGradient 辐射渐变
            // -------------------------
            shader = RadialGradient(
                300f,// 辐射中心的坐标X
                300f,// 辐射中心的坐标Y
                200f,// 辐射半径
                Color.RED,// 辐射中心的颜色
                Color.BLUE,// 辐射边缘的颜色
                Shader.TileMode.CLAMP // 辐射范围之外的着色模式。
            )
            // -------------------------
            // SweepGradient 扫描渐变
            // -------------------------
            val shaderSweep = SweepGradient(
                300f,// 扫描的中心X
                300f,// 扫描的中心Y
                Color.RED,// 扫描的起始颜色
                Color.WHITE// 扫描的终止颜色
            )
            shader = shaderSweep
            // -------------------------
            // BitmapShader 位图着色器
            // 实用场景：结合canvas.drawCircle来绘制出圆形图片
            // -------------------------
            val bmp = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            val bitmapShader = BitmapShader(
                bmp,// 用来做模板的 Bitmap 对象
                Shader.TileMode.CLAMP, // 横向的 TileMode
                Shader.TileMode.CLAMP // 纵向的 TileMode 。
            )
            shader = bitmapShader
            // -------------------------
            // ComposeShader 混合着色器
            // 实用场景：可以给图片添加水印
            // https://i.ibb.co/ZY1RgLM/i-Shot2022-02-07-19-29-18.png
            // ComposeShader()在硬件加速下是不支持两个
            // 相同类型的 Shader 的，所以需要关闭硬件加速才能看到效果。
            // -------------------------
            // 具体使用参考ComposeShaderView
            // =========================

        }
    }

}

/**
 * 演示ComposeShader的使用
 */
class ComposeShaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val shader1 = BitmapShader(
        BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong),
        Shader.TileMode.REPEAT, Shader.TileMode.REPEAT
    )

    private val shader2 = BitmapShader(
        BitmapFactory.decodeResource(resources, R.mipmap.ball),
        Shader.TileMode.REPEAT, Shader.TileMode.REPEAT
    )

    private val paint = Paint().apply {
        shader = ComposeShader(
            shader1, shader2, PorterDuff.Mode.SRC_OVER
        )
    }

    init {
        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300f, 300f, 300f, paint)
    }

}

class PorterDuffModeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    init {

    }

}