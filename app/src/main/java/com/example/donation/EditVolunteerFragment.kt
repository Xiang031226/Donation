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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.Campaign.Volunteer.VolunteerViewModel
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentEditVolunteerBinding
import com.example.donation.model.Volunteer
import com.example.donation.model.VolunteerRole
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*


class EditVolunteerFragment : HideBarOrTab() {

    private lateinit var binding: FragmentEditVolunteerBinding
    private lateinit var viewModel: VolunteerViewModel
    private lateinit var availableRoleList: List<VolunteerRole>

    //Predefined job roles (get from database)
    private var roleList = arrayListOf<String>("AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE")

    //ArrayList to store edited available roles
    private var editedRoleList = arrayListOf<VolunteerRole>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_volunteer, container, false)

        //Initialize and setup UI
        initView(view)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAppBar()
        hideTab(R.id.campaign_tab_layout)
        hideBottomBar(R.id.admin_bottom_nav_bar)

        binding = FragmentEditVolunteerBinding.bind(view)

        binding.apply {
            //Top Bar
            editVolTopBar.apply {
                adminCustomTopBarTitle.text = "Edit Volunteer"
                adminBackButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
            //Scroll
            val initialScrollY = editVolScroll.scrollY
            editVolScroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY == initialScrollY) {
                    // The scroll has returned to the initial position
                    editVolTopBarHl.visibility = View.GONE
                } else {
                    editVolTopBarHl.visibility = View.VISIBLE
                }
            }

            editVolDatePicker.setOnClickListener {
                pickDate()
            }
            editVolTimePicker.setOnClickListener {
                pickTime()
            }
            editVolLocationPicker.setOnClickListener {
                pickLocation()
            }

            editVolAddRoleButton.setOnClickListener {
                //Add layout for new available role
                val roleItem = LayoutInflater.from(context)
                    .inflate(R.layout.role_list_item, null) as MaterialCardView
                editVolAddRoleContainer.addView(roleItem)

                //Setup for each role item added
                setupRoleItem(roleItem)
            }

            editVolConfirmButton.setOnClickListener {
                //Get all the added available roles into a arrayList of VolunteerRole
                getEditedRoleItems()
                updateEditedRoleItem()
                findNavController().popBackStack()
            }

            editVolCancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        //Back pressed it will navigate back to the Volunteer main fragment
        requireActivity().onBackPressedDispatcher.addCallback(this@EditVolunteerFragment) {
            findNavController().popBackStack()
        }
    }

    private fun initView(view: View) {
        val eventImage = view.findViewById<ImageView>(R.id.edit_vol_image)
        val eventTitle = view.findViewById<EditText>(R.id.edit_vol_title_edit)
        val eventDate = view.findViewById<TextView>(R.id.edit_vol_date_picker)
        val eventTime = view.findViewById<TextView>(R.id.edit_vol_time_picker)
        val eventLocation = view.findViewById<TextView>(R.id.edit_vol_location_picker)
        val addRoleContainer = view.findViewById<LinearLayout>(R.id.edit_vol_add_role_container)

        viewModel = ViewModelProvider(requireActivity())[VolunteerViewModel::class.java]
        viewModel.selectedEvent.observe(viewLifecycleOwner) { selectedEvent ->
            eventImage.setImageResource(selectedEvent.eventImage)
            eventTitle.setText(selectedEvent.eventTitle)
            eventDate.text = selectedEvent.eventDate
            eventTime.text = selectedEvent.eventTime
            eventLocation.text = selectedEvent.eventLocation
            availableRoleList = selectedEvent.availableVolunteerRole

            //Add existing available roles of the selected volunteer event
            for (item in availableRoleList) {
                val roleItem = LayoutInflater.from(context)
                    .inflate(R.layout.role_list_item, null) as MaterialCardView
                addRoleContainer.addView(roleItem)
                setupRoleItem(roleItem, item.role, item.qtyNeeded.toString())
            }
        }
    }

    private fun pickDate() {
        // Parse the date string from the TextView into a Date object
        val dateString = binding.editVolDatePicker.text.toString()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        // Set the default date for the DatePickerDialog to the parsed date
        val calendar = Calendar.getInstance()
        calendar.time = date

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Update the date TextView with the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val selectedDateString = dateFormat.format(selectedDate.time)
                binding.editVolDatePicker.text = selectedDateString
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun pickTime() {
        val calendar = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

        // Parse the time string from the TextView into a Calendar object
        val timeString = binding.editVolTimePicker.text.toString()
        calendar.time = timeFormat.parse(timeString)!!

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                // Update the Calendar object with the new time
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                // Format the time and set it as the text of the TextView
                val selectedTime = timeFormat.format(calendar.time)
                binding.editVolTimePicker.text = selectedTime
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun pickLocation() {
        TODO("Not yet implemented")
    }

    private fun setupRoleItem(roleItem: View, roleTitle: String? = null, qtyNeeded: String = "1") {
        //Spinner adapter
        val spinner = roleItem.findViewById<Spinner>(R.id.role_item_spinner)
        roleList.sort()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            roleList
        )
        spinner.adapter = adapter
        //Initial role title
        val index = roleList.indexOf(roleTitle)
        if (roleTitle == null || index < 0) {
            spinner.setSelection(0)
        } else {
            spinner.setSelection(index)
        }

        //Quantity
        val minusBtn = roleItem.findViewById<ImageButton>(R.id.role_item_minus_button)
        val addBtn = roleItem.findViewById<ImageButton>(R.id.role_item_add_button)
        val qtyText = roleItem.findViewById<TextView>(R.id.role_item_quantity)
        //Initial quantity needed for that specific role
        qtyText.text = qtyNeeded

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
        val container = binding.editVolAddRoleContainer
        deleteBtn.setOnClickListener {
            deleteBtn.setImageResource(R.drawable.filled_delete)
            container.removeView(roleItem)
        }
    }

    private fun getEditedRoleItems() {
        binding.apply {
            for (i in 0 until editVolAddRoleContainer.childCount) {
                val editRoleItem = editVolAddRoleContainer.getChildAt(i)
                if (editRoleItem is MaterialCardView) {
                    val spinner = editRoleItem.findViewById<Spinner>(R.id.role_item_spinner)
                    val qtyText = editRoleItem.findViewById<TextView>(R.id.role_item_quantity)

                    val selectedNewRole = spinner.selectedItem.toString()
                    val qty = qtyText.text.toString().toInt()

                    // Create an instance of VolunteerRole and add it to the editRoleList
                    val editVolunteerRole =
                        VolunteerRole(selectedNewRole, listOf(""), listOf(""), qty)
                    editedRoleList.add(editVolunteerRole)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun updateEditedRoleItem() {
        binding.apply {
//            val eventImage = editVolImage.id
            val eventImage = viewModel.selectedEvent.value!!.eventImage
            val eventTitle = editVolTitleEdit.text.toString()
            val eventDate = editVolDatePicker.text.toString()
            val eventTime = editVolTimePicker.text.toString()
            val eventLocation = editVolLocationPicker.text.toString()

            //Update viewmodel
            val updatedVolunteer = Volunteer(
                eventImage,
                eventTitle,
                eventDate,
                eventTime,
                eventLocation,
                editedRoleList
            )
            viewModel.selectedEvent.value = updatedVolunteer
            Log.i("eventImage", updatedVolunteer.eventImage.toString())
            Log.i("eventTitle", updatedVolunteer.eventTitle.toString())
            Log.i("eventDate", updatedVolunteer.eventDate.toString())
            Log.i("eventTime", updatedVolunteer.eventTime.toString())
            Log.i("editedRoleList", updatedVolunteer.availableVolunteerRole.toString())
        }
    }
}