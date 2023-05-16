package com.example.donation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.model.History
import java.util.*

class HistoryCardAdapter(
    private var historyList: List<History>,
) : RecyclerView.Adapter<HistoryCardAdapter.HistoryCardViewHolder>() {

    private var showAllItems = false
    private var onItemClickListener: HistoryItemClickListener? = null
    private var prevExpandedPos = -1

    inner class HistoryCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val historyTitle: TextView = view.findViewById(R.id.history_title)
        private val historyDate: TextView = view.findViewById(R.id.history_date)
        private val historyTime: TextView = view.findViewById(R.id.history_time)
        private val amountDonated: TextView = view.findViewById(R.id.amount_donated)
        private val donationType: TextView = view.findViewById(R.id.donation_type)
        private val paymentMethod: TextView = view.findViewById(R.id.payment_method)
        private val category: TextView = view.findViewById(R.id.category)
        val historyContainer: ConstraintLayout = view.findViewById(R.id.history_container)
        val expandableLayout: RelativeLayout = view.findViewById(R.id.expandable_layout)

        fun bind(item: History, listener: HistoryItemClickListener?) {
            historyTitle.text = item.donation_title
            historyDate.text = item.donation_date
            historyTime.text = item.donation_time
            amountDonated.text = item.amount_donated.toString()
            donationType.text = item.description.donation_type
            paymentMethod.text = item.description.payment_method
            category.text = item.description.category

            // Call the click listener on item click
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_donation_history, parent, false)
        return HistoryCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryCardViewHolder, @SuppressLint("RecyclerView") position: Int) {
        if (showAllItems) {
            holder.bind(historyList[position], onItemClickListener)

            var isExpandable = historyList[position].isExpandable
            holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

            holder.historyContainer.setOnClickListener{
                val currentHistory = historyList[position]
                if (prevExpandedPos != RecyclerView.NO_POSITION && prevExpandedPos != position) {
                    historyList[prevExpandedPos].isExpandable = false
                    notifyItemChanged(prevExpandedPos)
                }

                prevExpandedPos = position
                currentHistory.isExpandable = !currentHistory.isExpandable
                notifyItemChanged(position)
            }

        } else {
            val lastTwoItems = historyList.takeLast(2)
            val currentItem = lastTwoItems.getOrNull(position)
            if (currentItem != null) {
                holder.bind(currentItem, onItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (showAllItems) {
            historyList.size
        } else {
            if (historyList.size > 2) 2 else historyList.size
        }
    }

    fun setOnItemClickListener(listener: HistoryItemClickListener) {
        onItemClickListener = listener
    }

    fun setShowAllItems(showAllItems: Boolean) {
        this.showAllItems = showAllItems
        notifyDataSetChanged()
    }

    fun updateList(newList: List<History>) {
        historyList = newList
        notifyDataSetChanged()
    }
}

interface HistoryItemClickListener {
    fun onItemClick(position: Int)
}