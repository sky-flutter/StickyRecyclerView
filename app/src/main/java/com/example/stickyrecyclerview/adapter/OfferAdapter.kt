package com.example.stickyrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyrecyclerview.R
import kotlinx.android.synthetic.main.item_claim_offers.view.*


class OfferAdapter : RecyclerView.Adapter<OfferAdapter.MyRecyclerViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        mContext = parent.context
        val mItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_claim_offers, parent, false)
        return MyRecyclerViewHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.apply {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flClaimOffer: FrameLayout = itemView.fl_claim_offer
        val btnClaim: Button = itemView.btn_claim
        val tvClaimOfferTitle: TextView = itemView.tv_claim_offer_title
        val tvClaimOfferPrice: TextView = itemView.tv_claim_offer_price
    }
}