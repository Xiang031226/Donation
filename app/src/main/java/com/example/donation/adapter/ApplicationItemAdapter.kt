package com.example.donation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.model.Application
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView

class ApplicationItemAdapter(
    private val context: Context?,
    private val applicationList: ArrayList<Application>
) :
    RecyclerView.Adapter<ApplicationItemAdapter.ApplicationItemViewHolder>() {

    class ApplicationItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val profilePic: CircleImageView = view.findViewById(R.id.item_profile_pic)
        val username: TextView = view.findViewById(R.id.item_username)
        val jobRole: TextView = view.findViewById(R.id.item_job_role)
        private val sendEmail: Button = view.findViewById(R.id.send_email_button)
        private val approve: ImageButton = view.findViewById(R.id.approve_button)

        init {
            sendEmail.setOnClickListener {
                Toast.makeText(view.context, "Email Sent", Toast.LENGTH_SHORT).show()
            }
            approve.setOnClickListener {
                approve.setImageResource(R.drawable.filled_check)
                Snackbar.make(view, "Application Approved", Snackbar.LENGTH_SHORT).apply {
                    setAction("UNDO") {
                        approve.setImageResource(R.drawable.outline_check)
                    }
                    show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.application_list_item, parent, false)

        return ApplicationItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return applicationList.size
    }

    override fun onBindViewHolder(holder: ApplicationItemViewHolder, position: Int) {
        val applicationItem = applicationList[position]
        holder.profilePic.setImageResource(applicationItem.profilePicResId)
        holder.username.text = applicationItem.username
        holder.jobRole.text = applicationItem.jobRole
    }
}