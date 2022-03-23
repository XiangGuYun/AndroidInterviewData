package com.yxd.devlib.view.index

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.yxd.R
import com.yxd.devlib.view.rv.EasyRecyclerView

/**
 * A-Z的索引列表
 */
@SuppressLint("ClickableViewAccessibility")
class AzIndexView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    EasyRecyclerView(context, attrs, defStyleAttr) {

    private var selectedIndex = DEFAULT_SELECTED_LETTER_INDEX

    private var onLetterChangeListener: ((letter: Char) -> Unit)? = null

    private val letterYAxisPosition = mutableListOf<Float>()

    private val letters = (START_LETTER..END_LETTER).toMutableList()

    init {
        letters.add(' ')
        generate(
            letters,
            { h, i, item ->
                h.setText(R.id.tvLetter, item.toString())
                if (i == letters.lastIndex && letterYAxisPosition.isEmpty()) {
                    post {
                        (0 until childCount).forEachIndexed { index, i ->
                            letterYAxisPosition.add(getChildAt(index).y)
                        }
                    }
                }
            },
            {
                if (selectedIndex == it) SELECTED_LAYOUT_INDEX else UNSELECT_LAYOUT_INDEX
            }, R.layout.item_index, R.layout.item_index_selected
        )


    }

    fun setOnLetterChangeListener(onLetterChangeListener: ((letter: Char) -> Unit)?) {
        this.onLetterChangeListener = onLetterChangeListener
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        e ?: return false
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                return update(e)
            }
            MotionEvent.ACTION_MOVE -> {
                return update(e)
            }
        }
        return true
    }

    private fun update(e: MotionEvent): Boolean {
        val found = letterYAxisPosition.find {
            it > e.y
        }
        if(found != null){
            val index = letterYAxisPosition.indexOf(found)
            if(selectedIndex != index && index != letterYAxisPosition.lastIndex){
                selectedIndex = index
                adapter?.notifyDataSetChanged()
                onLetterChangeListener?.invoke(letters[selectedIndex])
                return true
            }
        }
//        letterYAxisPosition.forEachIndexed { index, f ->
//            if (index != letterYAxisPosition.lastIndex
//                && e.y > f
//                && e.y < letterYAxisPosition[index + 1]
//                && selectedIndex != index
//            ) {
//                Log.d("Test", "selectedIndex is${selectedIndex}")
//                selectedIndex = index
//                adapter?.notifyDataSetChanged()
//                return true
//            }
//        }
        return false
    }

    companion object {
        const val START_LETTER = 'A'
        const val END_LETTER = 'Z'
        const val UNSELECT_LAYOUT_INDEX = 0
        const val SELECTED_LAYOUT_INDEX = 1
        const val DEFAULT_SELECTED_LETTER_INDEX = 0
    }

}