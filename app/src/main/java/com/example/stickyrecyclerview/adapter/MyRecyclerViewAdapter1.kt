package com.example.stickyrecyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyrecyclerview.MainActivity
import com.example.stickyrecyclerview.R
import com.example.stickyrecyclerview.model.Product
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.jar.Manifest

class MyRecyclerViewAdapter1(
    private var listProduct: ArrayList<Product>,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter1.MyRecyclerViewHolder>() {
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
        fun setSelection(llLayout: LinearLayout, pos: Int) {
            if (listProduct[pos].isSelected.value!=null && !listProduct[pos].isSelected.value!!) {
                llLayout.setBackgroundResource(R.drawable.shape_circle_outline)
                holder.tvTitle.setTextColor(Color.BLACK)
            } else {
                llLayout.setBackgroundResource(R.drawable.shape_circle_solid)
                holder.tvTitle.setTextColor(Color.WHITE)
            }
        }
        holder.llParent.apply {
            setOnClickListener {
                setSelection(this, position)
                onItemClick(position)
            }
        }

        listProduct[position].isSelected.observe(lifecycleOwner, Observer<Boolean>{
            setSelection(holder.llParent,position)
        })

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setListProduct(listProduct: ArrayList<Product>) {
        this.listProduct = listProduct
        notifyDataSetChanged()
    }

    class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llParent: LinearLayout = itemView.ll_parent
        val tvTitle: TextView = itemView.tv_title
    }
}