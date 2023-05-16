package com.example.donation.adapter

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.model.Faqs

class FaqsCardAdapter(
    private val faqsList: List<Faqs>
) : RecyclerView.Adapter<FaqsCardAdapter.FaqsCardViewHolder>() {

    inner class FaqsCardViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val question : TextView = view.findViewById(R.id.question)
        val answer : TextView = view.findViewById(R.id.answer)
        val arrowIcon : ImageView = view.findViewById(R.id.arrow_icon)
        val faqsContainer : ConstraintLayout = view.findViewById(R.id.faqs_container)
        val expandableLayout : RelativeLayout = view.findViewById(R.id.expandableAnswer_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqsCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.faqs_list, parent, false)

        return FaqsCardViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FaqsCardViewHolder, position: Int) {
        val currentItem = faqsList[position]

        holder.question.text = "${position + 1}. " + currentItem.question
        holder.answer.text = currentItem.answer

        val isExpanded = currentItem.isExpanded
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

        val arrowIcon = if (currentItem.isExpanded) {
            R.drawable.ic_arrow_up
        }
        else {
            R.drawable.ic_arrow_expand
        }
        holder.arrowIcon.setImageResource(arrowIcon)

        holder.faqsContainer.setOnClickListener {
            currentItem.isExpanded = !currentItem.isExpanded

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return faqsList.size
    }
}