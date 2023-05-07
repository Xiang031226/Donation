package com.example.donation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.data.VolunteerSource


class VolunteerCardAdapter(
    private val listener: VolunteerItemClickListener,      //passing listener
) : RecyclerView.Adapter<VolunteerCardAdapter.VolunteerCardViewHolder>() {

    private val volunteerList = VolunteerSource.volunteer

    class VolunteerCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val imageView: ImageView? = view!!.findViewById(R.id.item_image)
        val eventTitle: TextView? = view!!.findViewById(R.id.event_title)
        val date: TextView? = view!!.findViewById(R.id.date)
        val time: TextView? = view!!.findViewById(R.id.time)
        val location: TextView? = view!!.findViewById(R.id.location)
        val volunteerButton: Button? = view!!.findViewById(R.id.volunteer_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.volunteer_list_item,
            parent, false)
        return VolunteerCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: VolunteerCardViewHolder, position: Int) {
        val volunteerData = volunteerList[position]
        holder.eventTitle?.text = volunteerData.title
        holder.imageView?.setImageResource(volunteerData.titleImage)
        holder.date?.text = volunteerData.date
        holder.time?.text = volunteerData.time
        holder.location?.text = volunteerData.location

        holder.volunteerButton?.setOnClickListener {
            listener.onItemClick()
        }
    }

    override fun getItemCount(): Int {
        return volunteerList.size
    }

}

//creating interface, so my button of recycler view can be clicked:)
interface VolunteerItemClickListener {
    fun onItemClick()
}