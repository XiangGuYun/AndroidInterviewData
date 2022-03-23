package com.yxd.devlib.utils

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

/**
 * 检查所有必填的EditText是否已填
 */
object CheckMustFillUtils {

    /**
     * 检查
     * @param ets Array<EditText> EditText数组
     * @param onAllFill Function0<Unit> 当全部已填
     * @param onAllUnFill Function0<Unit> 当未全部已填
     */
    fun check(ets:Array<EditText>, onAllFill:()->Unit, onAllUnFill:()->Unit){
        ets.forEach {
            it.addTextChangedListener {
                if(ets.find { it.text.isEmpty() } == null){
                    onAllFill.invoke()
                } else {
                    onAllUnFill.invoke()
                }
            }
        }
    }

}