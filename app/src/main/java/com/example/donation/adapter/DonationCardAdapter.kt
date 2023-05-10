package com.example.donation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.DonationViewModel
import com.example.donation.R
import com.example.donation.model.Donation

class DonationCardAdapter(
    private val viewModel: DonationViewModel,                  //viewModel, to update the destination fragment, kinda
    private val donationList: List<Donation>,                  //my animal description lists
    private val listener: DonationItemClickListener            //passing listener
): RecyclerView.Adapter<DonationCardAdapter.DonationCardViewHolder>() {

    class DonationCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val imageView: ImageView? = view!!.findViewById(R.id.item_image)
        val donationTitle: TextView? = view!!.findViewById(R.id.donation_title)
        val donationCategory: TextView? = view!!.findViewById(R.id.donation_category)
        val donateButton: Button? = view!!.findViewById(R.id.donate_button)
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

        holder.donateButton?.setOnClickListener{
            viewModel.selectedAnimal.value = donationData  //assign the animal which the user has clicked to my viewModel mutable live data
            listener.onItemClick()                         //and then navigate to the description fragment
        }
    }

    override fun getItemCount(): Int {
        return donationList.size
    }
}

//creating interface, so my button of recycler view can be clicked:)
interface DonationItemClickListener {
    fun onItemClick()
}