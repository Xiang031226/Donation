package com.example.donation.ReusableResource

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.donation.CardViewModel
import com.example.donation.Payment.PaymentViewModel
import com.example.donation.R
import com.example.donation.data.CardPayment
import com.example.donation.databinding.FragmentOpenDialogBinding

class OpenDialog : DialogFragment() {

    private lateinit var binding: FragmentOpenDialogBinding
    private lateinit var viewModel: CardViewModel

    data class DialogData(val cardNumber : String, val expiredDate : String, val cvv : Int)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // Set the custom layout for the dialog
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_open_dialog, null)

        // Set the dialog view and adjust its width
        dialog.setContentView(view)
        dialog.window?.setLayout(
            resources.getDimensionPixelSize(R.dimen.dialog_width),
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOpenDialogBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[CardViewModel::class.java]

        binding.saveButton.setOnClickListener {

            insertCardIntoDatabase()
            dialog?.dismiss()
//            val formattedCardNumber = "*" + cardNumber.substring(cardNumber.length - 4)
//            val data = DialogData(formattedCardNumber, expiredDate, cvv)
//            viewModel.setDialogData(data)
//            dialog?.dismiss()
        }
    }

    private fun insertCardIntoDatabase() {
        val cardNumber = binding.cardNumberInput.text.toString()
        val expiredDate = binding.expiryDateInput.text.toString()
        val cvv = binding.cvvInput.text.toString().toInt()

        if (inputCheck(cardNumber, expiredDate, cvv)) {
            val card = CardPayment(cardNumber = cardNumber, expiredDate = expiredDate, cvv = cvv)
            viewModel.addCard(card)
            Toast.makeText(activity, "card added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(cardNumber: String, expiredDate: String, cvv: Int): Boolean {
        return cardNumber.isNotEmpty() && expiredDate.isNotEmpty() && cvv > 100
    }



}