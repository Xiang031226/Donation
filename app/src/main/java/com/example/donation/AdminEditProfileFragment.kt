package com.example.donation

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.donation.dao.UserDao
import com.example.donation.databinding.FragmentAdminEditProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class AdminEditProfileFragment : Fragment(R.layout.fragment_admin_edit_profile) {

    private lateinit var binding: FragmentAdminEditProfileBinding
    private lateinit var userDao: UserDao
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
        return inflater.inflate(R.layout.fragment_admin_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminEditProfileBinding.bind(view)
        profileImageView = binding.adminEditPfp

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

        binding.apply {
            profileImageView = adminEditPfp

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
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun saveToDatabase() {
        val editedImageByteArray = imageBitmap?.let { bitmapToByteArray(it) }
        var editedName = binding.adminEditProfNameText.text.toString().trim()
        var editedUsername = binding.adminEditProfUsernameText.text.toString().trim()
        val editedEmail = binding.adminEditProfEmailText.text.toString().trim()

        lifecycleScope.launch(Dispatchers.IO) {
            //get the user from db
//            val user = userDao.getById(1)
//            user?.let {
//                val updatedUser =
//                    user.copy(
//                        profilePic = editedImageByteArray,
//                        name = editedName,
//                        username = editedUsername,
//                        email = editedEmail
//                    )
//                userDao.update(updatedUser) // Update the user record in the database
//            }
        }
    }
}