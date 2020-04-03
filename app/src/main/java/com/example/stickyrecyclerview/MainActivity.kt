package com.example.stickyrecyclerview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyrecyclerview.adapter.MyRecyclerViewAdapter
import com.example.stickyrecyclerview.adapter.MyRecyclerViewAdapter1
import com.example.stickyrecyclerview.adapter.MyRecyclerViewAdapter2
import com.example.stickyrecyclerview.adapter.ViewPagerAdapter
import com.example.stickyrecyclerview.model.Product
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var height1: Int = 0
    private lateinit var myRecyclerViewAdapter2: MyRecyclerViewAdapter2
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private lateinit var myRecyclerViewAdapter: MyRecyclerViewAdapter
    private lateinit var myRecyclerViewAdapter1: MyRecyclerViewAdapter1
    private lateinit var mRv1ScrollListener: RecyclerView.OnScrollListener
    private lateinit var mStickyScrollListener: RecyclerView.OnScrollListener
    private val listProduct = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createProductData()
        handleScrolling()
        setAdapter()
        calculateHeightOfStickyView()
    }


    private fun calculateHeightOfStickyView() {
        var height = supportActionBar?.height?.plus(getStatusBarHeight())
        height1 = 0
        nsv_scroll.post {
            val ll = nsv_scroll.getChildAt(0) as LinearLayout
            height1 = 0
            for (i in 0 until ll.childCount - 1) {
                if (ll[i].tag == getString(R.string.recyclerview_1)) {
                    height = height?.plus(ll[i].height - ll[i].marginTop - ll[i].marginBottom)
                    height1 = height!!
                } else {
                    height = height?.plus(ll[i].height + ll[i].marginBottom + ll[i].marginTop)
                }
            }
        }
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun handleScrolling() {
        mRv1ScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                rv_sticky.removeOnScrollListener(mStickyScrollListener)
                rv_sticky.scrollBy(dx, dy)
                rv_sticky.addOnScrollListener(mStickyScrollListener)
            }
        }
        mStickyScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                rv_1.removeOnScrollListener(mRv1ScrollListener)
                rv_1.scrollBy(dx, dy)
                rv_1.addOnScrollListener(mRv1ScrollListener)
            }
        }

        nsv_scroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.d("HEIGHT", "$height1")
            if (scrollY > (height1 - 100)) {
                rv_sticky.scrollTo(rv_1.scrollX, rv_1.scrollY)
                //Show Sticky View
                rv_sticky.alpha = 1.0f
            } else {
                //Hide Sticky View
                rv_sticky.alpha = 0.0f
            }
        }
        rv_1.addOnScrollListener(mRv1ScrollListener)
    }

    private fun setAdapter() {
        nsv_scroll.isEnabled = true
        rv_1.isFocusable = false
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, this)
        myRecyclerViewAdapter = MyRecyclerViewAdapter(listProduct, this)
        myRecyclerViewAdapter1 =
            MyRecyclerViewAdapter1(listProduct, this) { position ->
                listProduct[position].isSelected.value =
                    !(listProduct[position].isSelected.value != null && listProduct[position].isSelected.value!!)
            }

        myRecyclerViewAdapter2 =
            MyRecyclerViewAdapter2(listProduct, this) { position ->
                listProduct[position].isSelected.value =
                    !(listProduct[position].isSelected.value != null && listProduct[position].isSelected.value!!)
            }
        rv_1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sticky.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_1.adapter = myRecyclerViewAdapter1
        rv_2.adapter = myRecyclerViewAdapter
        rv_3.adapter = myRecyclerViewAdapter
        rv_sticky.adapter = myRecyclerViewAdapter2

        vp_1.adapter = mViewPagerAdapter
        vp_2.adapter = mViewPagerAdapter
        vp_3.adapter = mViewPagerAdapter
        vp_4.adapter = mViewPagerAdapter

    }

    private fun createProductData() {
        for (i in 0..9) {
            val product = Product()
            product.strName = "Index $i"
            listProduct.add(product)
        }
    }
}
