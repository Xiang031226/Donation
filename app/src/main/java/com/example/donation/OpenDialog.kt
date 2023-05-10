package com.example.donation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.donation.databinding.FragmentOpenDialogBinding

class OpenDialog : DialogFragment() {

    private lateinit var binding: FragmentOpenDialogBinding

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
        binding.saveButton.setOnClickListener {
            dialog?.dismiss()
        }
    }



}