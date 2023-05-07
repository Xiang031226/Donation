package com.example.donation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentDonationDescriptionBinding

class DonationDescriptionFragment : HideBarOrTab() {

    private lateinit var binding: FragmentDonationDescriptionBinding
    private lateinit var viewModel: AnimalViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_donation_description, container, false)

        val titleImage = view.findViewById<ImageView>(R.id.title_image)
        val image2 = view.findViewById<ImageView>(R.id.image2)
        val image3 = view.findViewById<ImageView>(R.id.image3)
        val extTitle = view.findViewById<TextView>(R.id.extinction_title)
        val threatTitle = view.findViewById<TextView>(R.id.threat_title)
        val mustDoTitle = view.findViewById<TextView>(R.id.mustDo_title)
        val awareness = view.findViewById<TextView>(R.id.awareness)
        val threatMsg = view.findViewById<TextView>(R.id.threat_msg)
        val mustDoMsg = view.findViewById<TextView>(R.id.mustDo_msg)
        val supportMsg = view.findViewById<TextView>(R.id.support_msg)
        val toolbar = view.findViewById<Toolbar>(R.id.custom_toolbar)

        viewModel = ViewModelProvider(requireActivity())[AnimalViewModel::class.java]
        viewModel.selectedAnimal.observe(viewLifecycleOwner) { selectedAnimal ->
            titleImage.setImageResource(selectedAnimal.titleImage)
            image2.setImageResource(selectedAnimal.image2)
            image3.setImageResource(selectedAnimal.image3)
            extTitle.text = selectedAnimal.extinctionTitle
            threatTitle.text = selectedAnimal.threatTitle
            mustDoTitle.text = selectedAnimal.mustDoTitle
            awareness.text = selectedAnimal.awareness
            threatMsg.text = selectedAnimal.threatMsg
            mustDoMsg.text = selectedAnimal.mustDoMsg
            supportMsg.text = selectedAnimal.supportMsg
            toolbar.title = selectedAnimal.title
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDonationDescriptionBinding.bind(view)

        hideTab()
        hideBottomBar()
        hideAppBar()

        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener{
            callBack()
        }

        binding.donateButton.setOnClickListener{
            findNavController().navigate(R.id.action_descriptionFragment_to_paymentFragment)
        }

    }

    private fun callBack() {
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        showTab()
        showAppBar()
        showBottomBar()
    }


}