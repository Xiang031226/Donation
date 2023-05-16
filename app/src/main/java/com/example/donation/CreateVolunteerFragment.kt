package com.example.donation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentCreateVolunteerBinding
import com.example.donation.model.Volunteer
import com.example.donation.model.VolunteerRole
import com.google.android.material.card.MaterialCardView
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateVolunteerFragment : HideBarOrTab() {

    private lateinit var binding: FragmentCreateVolunteerBinding

    //Predefined job roles (get from database)
    private var roleList = arrayListOf<String>(
        "Conservation Volunteer",
        "Community Outreach Volunteer",
        "Fundraising Volunteer",
        "Environmental Education Volunteer",
        "Wildlife Rescue Volunteer",
        "Research Volunteer",
        "Sustainable Development Volunteer"
    )

    //ArrayList to store added available roles
    private var newRoleList = arrayListOf<VolunteerRole>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_volunteer, container, false)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAppBar()
        hideTab(R.id.campaign_tab_layout)
        hideBottomBar(R.id.admin_bottom_nav_bar)

        binding = FragmentCreateVolunteerBinding.bind(view)

        binding.apply {
            //Top Bar
            createVolTopBar.apply {
                adminCustomTopBarTitle.text = "Create Volunteer"
                adminBackButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
            //Scroll
            val initialScrollY = createVolScroll.scrollY
            createVolScroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY == initialScrollY) {
                    // The scroll has returned to the initial position
                    createVolTopBarHl.visibility = View.GONE
                } else {
                    createVolTopBarHl.visibility = View.VISIBLE
                }
            }

            createVolDatePicker.setOnClickListener {
                pickDate()
            }
            createVolTimePicker.setOnClickListener {
                pickTime()
            }

            createVolAddRoleButton.setOnClickListener {
                //Add layout for new available role
                val roleItem = LayoutInflater.from(context).inflate(R.layout.role_list_item, null)
                createVolAddRoleContainer.addView(roleItem)

                //Setup for each role item added
                setupRoleItem(roleItem)
            }

            createVolConfirmButton.setOnClickListener {
                //Get all the added available roles into a arrayList of VolunteerRole
                getNewRoleItems()
                addNewVolunteer()
                findNavController().navigate(R.id.admin_volunteer_fragment)
            }

            createVolCancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        //Back pressed it will navigate back to the Volunteer main fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@CreateVolunteerFragment) {
            findNavController().popBackStack(R.id.admin_volunteer_fragment, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showAppBar()
        showTab(R.id.campaign_tab_layout)
        showBottomBar(R.id.admin_bottom_nav_bar)
    }

    private fun pickDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                // Convert the monthOfYear integer into month name
                val monthName = DateFormatSymbols().months[monthOfYear]
                // Update the text of the TextView with the selected date
                val selectedDate = "$dayOfMonth $monthName $year"
                binding.createVolDatePicker.text = selectedDate
            }, year, month, day)
        datePickerDialog.show()
    }

    private fun pickTime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
            val timeText = timeFormat.format(calendar.time)
            binding.createVolTimePicker.text = timeText
        }
        TimePickerDialog(requireContext(), timeSetListener, hour, minute, false).show()
    }

    private fun setupRoleItem(roleItem: View) {
        //Spinner adapter
        val spinner = roleItem.findViewById<Spinner>(R.id.role_item_spinner)
        roleList.sort()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            roleList
        )
        spinner.adapter = adapter

        //Quantity
        val minusBtn = roleItem.findViewById<ImageButton>(R.id.role_item_minus_button)
        val addBtn = roleItem.findViewById<ImageButton>(R.id.role_item_add_button)
        val qtyText = roleItem.findViewById<TextView>(R.id.role_item_quantity)

        minusBtn.setOnClickListener {
            val qtyIntValue = qtyText.text.toString().toInt()
            if (qtyIntValue > 1) {
                qtyText.text = (qtyIntValue - 1).toString()
            }
        }
        addBtn.setOnClickListener {
            val qtyIntValue = qtyText.text.toString().toInt()
            if (qtyIntValue < 10) {
                qtyText.text = (qtyIntValue + 1).toString()
            }
        }

        //Delete
        val deleteBtn = roleItem.findViewById<ImageButton>(R.id.role_item_delete_button)
        val container = binding.createVolAddRoleContainer
        deleteBtn.setOnClickListener {
            deleteBtn.setImageResource(R.drawable.filled_delete)
            container.removeView(roleItem)
        }
    }

    private fun getNewRoleItems() {
        binding.apply {
            for (i in 0 until createVolAddRoleContainer.childCount) {
                val newRoleItem = createVolAddRoleContainer.getChildAt(i)
                if (newRoleItem is MaterialCardView) {
                    val spinner = newRoleItem.findViewById<Spinner>(R.id.role_item_spinner)
                    val qtyText = newRoleItem.findViewById<TextView>(R.id.role_item_quantity)

                    val selectedNewRole = spinner.selectedItem.toString()
                    val qty = qtyText.text.toString().toInt()

                    // Create an instance of VolunteerRole and add it to the newRoleList
                    val newVolunteerRole =
                        VolunteerRole(selectedNewRole, listOf(""), listOf(""), qty)
                    newRoleList.add(newVolunteerRole)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun addNewVolunteer() {
        binding.apply {
            val eventImage = createVolImage.id
            val eventTitle = createVolTitleEdit.toString()
            val eventDate = createVolDatePicker.toString()
            val eventTime = createVolTimePicker.toString()
            val eventLocation = createVolLocationPicker.toString()

            //Add to database
            val newVolunteer = Volunteer(
                eventImage,
                eventTitle,
                eventDate,
                eventTime,
                eventLocation,
                newRoleList
            )
        }
    }
}