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
import com.example.donation.data.DonationTransaction
import java.util.*

class HistoryCardAdapter(
    private var transactionList: List<DonationTransaction> = emptyList()
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

        fun bind(item: DonationTransaction, listener: HistoryItemClickListener?) {
            historyTitle.text = item.donationTitle
            historyDate.text = item.date
            historyTime.text = item.time
            amountDonated.text = "RM " + item.amount.toString()
            donationType.text = item.donationType
            paymentMethod.text = item.paymentMethod
            category.text = item.category

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
            holder.bind(transactionList[position], onItemClickListener)

            var isExpandable = transactionList[position].isExpandable
            holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

            holder.historyContainer.setOnClickListener{
                val currentHistory = transactionList[position]
                if (prevExpandedPos != RecyclerView.NO_POSITION && prevExpandedPos != position) {
                    transactionList[prevExpandedPos].isExpandable = false
                    notifyItemChanged(prevExpandedPos)
                }

                prevExpandedPos = position
                currentHistory.isExpandable = !currentHistory.isExpandable
                notifyItemChanged(position)
            }

        } else {
            val lastTwoItems = transactionList.takeLast(2)
            val currentItem = lastTwoItems.getOrNull(position)
            if (currentItem != null) {
                holder.bind(currentItem, onItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (showAllItems) {
            transactionList.size
        } else {
            if (transactionList.size > 2) 2 else transactionList.size
        }
    }

    fun setOnItemClickListener(listener: HistoryItemClickListener) {
        onItemClickListener = listener
    }

    fun setShowAllItems(showAllItems: Boolean) {
        this.showAllItems = showAllItems
        notifyDataSetChanged()
    }

    fun updateList(newList: List<DonationTransaction>) {
        transactionList = newList
        notifyDataSetChanged()
    }
}

interface HistoryItemClickListener {
    fun onItemClick(position: Int)
}