package com.example.donation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.DescriptionFragment
import com.example.donation.R
import com.example.donation.data.DonationSource

class DonationCardAdapter(
    private val listener: RecyclerViewItemClickListener
): RecyclerView.Adapter<DonationCardAdapter.DonationCardViewHolder>() {

    private val donationList = DonationSource.donation

    class DonationCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val imageView : ImageView? = view!!.findViewById(R.id.item_image)
        val donationTitle : TextView? = view!!.findViewById(R.id.donation_title)
        val donationCategory : TextView? = view!!.findViewById(R.id.donation_category)
        val donateButton : Button? = view!!.findViewById(R.id.donate_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.donation_list_item,
            parent, false)
        return DonationCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: DonationCardViewHolder, position: Int) {
        val donationData = donationList[position]
        holder.donationTitle?.text = donationData.title
        holder.imageView?.setImageResource(donationData.imageResourceId)
        holder.donationCategory?.text = donationData.category
        holder.donateButton?.setOnClickListener{
            listener.onItemClick(DescriptionFragment())
        }
    }

    override fun getItemCount(): Int {
        return donationList.size
    }

}

interface RecyclerViewItemClickListener {
    fun onItemClick(fragment: Fragment)
}