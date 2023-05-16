package com.example.donation.Payment

import android.app.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.donation.ReusableResource.OpenDialog

class PaymentViewModel: ViewModel() {

    private val _dialogData = MutableLiveData<OpenDialog.DialogData>()
    val dialogData: LiveData<OpenDialog.DialogData> = _dialogData

    fun setDialogData(data: OpenDialog.DialogData) {
        _dialogData.value = data
    }

}