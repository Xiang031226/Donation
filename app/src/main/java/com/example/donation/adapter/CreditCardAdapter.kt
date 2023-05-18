package com.example.donation.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.donation.CardViewModel
import com.example.donation.R
import com.example.donation.data.CardPayment
import com.example.donation.model.CreditCard
import kotlin.coroutines.coroutineContext

class CreditCardAdapter(
    private val viewModel: CardViewModel,
    private var cardList: List<CardPayment> = emptyList(),
) : RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder>() {

    inner class CreditCardViewHolder(view : View) : RecyclerView.ViewHolder(view) {
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

        holder.cardNumber.text = currentItem.cardNumber

        holder.deleteImage.setOnClickListener {
            viewModel.deleteCard(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setData(card : List<CardPayment>) {
        this.cardList = card
        notifyDataSetChanged()
    }
}

