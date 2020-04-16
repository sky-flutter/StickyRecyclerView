package com.example.stickyrecyclerview

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stickyrecyclerview.adapter.MyRecyclerViewAdapter
import com.example.stickyrecyclerview.model.Product
import kotlinx.android.synthetic.main.activity_main1.*
import java.util.*

class StickyScrollViewActivity : AppCompatActivity() {
    var str: String = "ff0f3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        Handler().postDelayed({
            var myRecyclerViewAdapter = MyRecyclerViewAdapter(createProductData(), this)
            rvsticky.apply {
                layoutManager = LinearLayoutManager(
                    this@StickyScrollViewActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = myRecyclerViewAdapter
            }
        }, 2000)
    }


    private fun createProductData(): ArrayList<Product> {
        val listProduct = ArrayList<Product>()
        for (i in 0..9) {
            val product = Product()
            product.strName = "Index $i"
            listProduct.add(product)
        }
        return listProduct
    }
}
