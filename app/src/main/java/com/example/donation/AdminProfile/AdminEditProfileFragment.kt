package com.example.donation.AdminProfile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.donation.AccountViewModel
import com.example.donation.R
import com.example.donation.database.AppDatabase
import com.example.donation.databinding.FragmentAdminEditProfileBinding
import com.example.donation.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException

class AdminEditProfileFragment : Fragment(R.layout.fragment_admin_edit_profile) {

    private lateinit var binding: FragmentAdminEditProfileBinding
    private lateinit var viewModel: AccountViewModel
    private lateinit var profileImageView: ImageView
    private lateinit var selectPictureLauncher: ActivityResultLauncher<String>
    private var imageBitmap: Bitmap? = null

    companion object {
        private const val REQUEST_SELECT_PICTURE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_edit_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminEditProfileBinding.bind(view)
        profileImageView = binding.adminEditPfp
        viewModel = ViewModelProvider(requireActivity())[AccountViewModel::class.java]

        //Initialize ActivityResultLauncher
        selectPictureLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    //Get imageBitMap of the selected picture
                    imageBitmap = getBitmapFromUri(uri)!!

                    //Show it in the Imageview
                    profileImageView.setImageBitmap(imageBitmap)
                }
            }

        val profilePic = viewModel.currentUser.value!!.profilePic
        val usernameText = viewModel.currentUser.value!!.username
        val nameText = viewModel.currentUser.value!!.name
        val emailText = viewModel.currentUser.value!!.email

        if (byteArrayToBitmap(profilePic) != null) {
            imageBitmap = byteArrayToBitmap(profilePic)
            binding.adminEditPfp.setImageBitmap(imageBitmap)
        } else {
            setDefaultProfilePic()
        }

        binding.apply {
            adminEditProfUsernameText.setText(usernameText)
            adminEditProfNameText.setText(nameText)
            adminEditProfEmailText.setText(emailText)

            adminEditProfTopBar.apply {
                adminCustomTopBarTitle.text = "Edit Profile"
                adminBackButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
            adminRemovePfpText.setOnClickListener {
                setDefaultProfilePic()
            }

            adminEditPfpText.setOnClickListener {
                selectPictureLauncher.launch("image/*")
            }

            adminEditProfConfirmButton.setOnClickListener {
                findNavController().popBackStack()
                // Save the image to Room database
                saveToDatabase()
            }
            adminEditProfCancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setDefaultProfilePic() {
        profileImageView.setImageResource(R.drawable.image2)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.image2)
        imageBitmap = drawable?.toBitmap()!!
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireActivity().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream)
        return stream.toByteArray()
    }

    private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return try {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveToDatabase() {
        var editedName = binding.adminEditProfNameText.text.toString().trim()
        var editedUsername = binding.adminEditProfUsernameText.text.toString().trim()
        val editedEmail = binding.adminEditProfEmailText.text.toString().trim()

        val prefs = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            viewModel.currentUser.value?.name = editedName
            viewModel.currentUser.value?.username = editedUsername
            viewModel.currentUser.value?.email = editedEmail
            Log.i("Euser.name", user.name)
            Log.i("Euser.username", user.username)
            Log.i("Euser.email", user.email)
        }

        val editor = prefs.edit()
        editor.putString("username", editedUsername)
        editor.putString("email", editedEmail)
        editor.apply()
    }
}