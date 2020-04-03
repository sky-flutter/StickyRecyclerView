package com.example.stickyrecyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyrecyclerview.MainActivity
import com.example.stickyrecyclerview.R
import com.example.stickyrecyclerview.model.Product
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class MyRecyclerViewAdapter(
    private val listProduct: ArrayList<Product>,
    private val lifecycle: MainActivity
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        mContext = parent.context
        val mItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return MyRecyclerViewHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.tvTitle.text = listProduct[position].strName
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llParent: LinearLayout = itemView.ll_parent
        val tvTitle: TextView = itemView.tv_title
    }
}