package com.example.donation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.Campaign.Volunteer.VolunteerViewModel
import com.example.donation.model.Volunteer


class VolunteerCardAdapter(
    private val viewModel : VolunteerViewModel,
    private val eventList : List<Volunteer>,
    private val listener : VolunteerItemClickListener,      //passing listener
    private val buttonText: String = "Volunteer"
) : RecyclerView.Adapter<VolunteerCardAdapter.VolunteerCardViewHolder>() {

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
        val volunteerData = eventList[position]
        holder.eventTitle?.text = volunteerData.eventTitle
        holder.imageView?.setImageResource(volunteerData.eventImage)
        holder.date?.text = volunteerData.eventDate
        holder.time?.text = volunteerData.eventTime
        holder.location?.text = volunteerData.eventLocation
        holder.volunteerButton?.text = buttonText

        holder.volunteerButton?.setOnClickListener {
            viewModel.selectedEvent.value = volunteerData
            listener.onItemClick()
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

}

//creating interface, so my button of recycler view can be clicked:)
interface VolunteerItemClickListener {
    fun onItemClick()
}