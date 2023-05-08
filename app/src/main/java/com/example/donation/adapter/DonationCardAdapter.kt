package com.example.donation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.data.DonationSource
import javax.sql.DataSource

class DonationCardAdapter(
    private val context: Context?,
) : RecyclerView.Adapter<DonationCardAdapter.DonationCardViewHolder>() {

    private val donationList = DonationSource.donation

    class DonationCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val imageView: ImageView? = view!!.findViewById(R.id.item_image)
        val donationTitle: TextView? = view!!.findViewById(R.id.donation_title)
        val donationCategory: TextView? = view!!.findViewById(R.id.donation_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.donation_list_item,
            parent, false
        )
        return DonationCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: DonationCardViewHolder, position: Int) {
        val donationData = donationList[position]
        holder.donationTitle?.text = donationData.title
        holder.imageView?.setImageResource(donationData.imageResourceId)
        holder.donationCategory?.text = donationData.category
    }

    override fun getItemCount(): Int {
        return donationList.size
    }
}