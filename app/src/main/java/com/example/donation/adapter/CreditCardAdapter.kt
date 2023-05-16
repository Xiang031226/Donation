package com.example.donation.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.R
import com.example.donation.model.CreditCard
import kotlin.coroutines.coroutineContext

class CreditCardAdapter(
    private val context : Context,
    private val cardList: MutableList<CreditCard>
) : RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder>() {

    inner class CreditCardViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val cardImage: ImageView = view.findViewById(R.id.card_icon)
        val cardNumber : TextView = view.findViewById(R.id.card_number)
        val deleteImage : ImageView = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        val adapter = LayoutInflater.from(parent.context).inflate(R.layout.preference_credit_card_lists, parent, false)
        return CreditCardViewHolder(adapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        val currentItem = cardList[position]

        holder.cardImage.setImageResource(currentItem.cardImage)
        holder.cardNumber.text = currentItem.cardNumber
        holder.deleteImage.setImageResource(currentItem.deleteButton)

        holder.deleteImage.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure want to delete this card")
                .setPositiveButton("Yes") {
                    dialog,_->
                    cardList.removeAt(position)
                    notifyDataSetChanged()
                    dialog.dismiss()
                }
                .setNegativeButton("No") {
                    dialog, _->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

}
