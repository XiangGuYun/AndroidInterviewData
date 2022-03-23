package com.yxd.devlib.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.yxd.R
import com.yxd.devlib.utils.UiUtils

abstract class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_ll, container, false)
    }

    private lateinit var fragmentView: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView = view.findViewById(R.id.llParent)
        init(view, savedInstanceState)
    }

    abstract fun init(view: View, savedInstanceState: Bundle?)

    fun addView(
        view: View,
        width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    ) {
        view.layoutParams = LinearLayout.LayoutParams(
            width,
            height
        )
        fragmentView.addView(view)
    }

    fun addTextView(
        defText: String = "",
        width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
        height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    ): TextView {
        val textView = TextView(requireContext())
        textView.text = defText
        textView.layoutParams = LinearLayout.LayoutParams(
            width,
            height
        )
        fragmentView.addView(textView)
        return textView
    }

    fun addImageView(
        width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
        height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
        scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_INSIDE
    ): ImageView {
        val iv = ImageView(requireActivity()).apply {
            layoutParams = LinearLayout.LayoutParams(
                width,
                height
            )
            this.scaleType = scaleType
        }
        fragmentView.addView(iv)
        return iv
    }

    fun addButton(txt: String, onClick: View.OnClickListener? = null): AppCompatButton {
        val btn = AppCompatButton(requireActivity()).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            text = txt
            setOnClickListener(onClick)
        }
        fragmentView.addView(btn)
        return btn
    }

    @MainThread
    fun toast(string: String) {
        Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show()
    }

    val Number.dp: Int
        get() = UiUtils.dp2px(requireContext(), this)

    companion object {
        const val MP = ViewGroup.LayoutParams.MATCH_PARENT
        const val WC = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    fun logt(text: String?){
        Log.d("Test", text.toString())
    }

}