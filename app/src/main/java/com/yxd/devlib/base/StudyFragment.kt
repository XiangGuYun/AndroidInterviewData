package com.yxd.devlib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.yxd.R
import com.yxd.devlib.view.vp.Vp2Ex

abstract class StudyFragment : Fragment(), Vp2Ex{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_study, container, false)
    }

    private val list = ArrayList<TestFragment>()

    abstract fun getFragmentList(): List<TestFragment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.addAll(getFragmentList())
        view.findViewById<ViewPager2>(R.id.vpStudy).apply {
            setAllowUserInput(false)
            adapter = object:FragmentStateAdapter(requireActivity()){
                override fun getItemCount(): Int {
                    return list.size
                }

                override fun createFragment(position: Int): Fragment {
                    return list[position]
                }
            }
            bindTL(view.findViewById(R.id.tlStudy)){
                tab: TabLayout.Tab, i: Int ->
                tab.text = list[i].javaClass.simpleName
            }
        }

    }
}