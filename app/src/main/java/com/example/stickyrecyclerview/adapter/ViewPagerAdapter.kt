package com.example.stickyrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.stickyrecyclerview.R
import kotlinx.android.synthetic.main.item_viewpager.view.*

class ViewPagerAdapter(fm:FragmentManager,private val mContext:Context) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mView = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager,container,false)
        container.addView(mView)
        mView.tv_viewpager.text = "ViewPager $position"
        return mView
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    override fun getCount(): Int {
        return 8
    }
}