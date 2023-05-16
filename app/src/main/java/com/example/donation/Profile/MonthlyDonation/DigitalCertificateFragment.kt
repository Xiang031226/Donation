package com.example.donation.Profile.MonthlyDonation


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.example.donation.R
import com.example.donation.ReusableResource.HideBarOrTab
import com.example.donation.databinding.FragmentDigitalCertificateBinding
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPageEventHelper
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.text.Typography.paragraph


class DigitalCertificateFragment : HideBarOrTab() {

    var pageHeight = 1120
    var pageWidth = 792

    lateinit var bmp: Bitmap
    lateinit var scaledbmp : Bitmap

    private var permissionCode = 101
    private var SAVE_PDF_REQUEST_CODE = 102
    private var certificateFile: File? = null


    private lateinit var binding: FragmentDigitalCertificateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val filePath = sharedPreferences.getString("certificateFilePath", null)
        if (filePath != null) {
            certificateFile = File(filePath)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_digital_certificate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomBar()

        binding = FragmentDigitalCertificateBinding.bind(view)
        bmp = BitmapFactory.decodeResource(resources, R.drawable.file_base_image)
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140,140,false)

        if (checkPermissions()) {
            Toast.makeText(activity, "Permissions Granted", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }


        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }


        binding.downloadButton.setOnClickListener{
            certificateFile = File(requireContext().cacheDir, "Certificate.pdf")
            generatePDF(certificateFile!!)
        }

        binding.shareButton.setOnClickListener {
            if (certificateFile != null && certificateFile!!.exists()) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "application/pdf"
                shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(requireContext(), "com.example.donation.fileprovider", certificateFile!!))
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                startActivity(Intent.createChooser(shareIntent, "Share Certificate"))
            } else {
                Toast.makeText(activity, "File not found...", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun generatePDF(file:File) {

        val fileName = "Certificate.pdf"

        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "application/pdf"
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivityForResult(intent, SAVE_PDF_REQUEST_CODE)

        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("certificateFilePath", file.absolutePath).apply()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val document = Document()
        if (requestCode == SAVE_PDF_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val outputStream = requireContext().contentResolver.openOutputStream(uri)
                if (outputStream != null) {
                    try {
                        certificateFile = File(requireContext().cacheDir, "Certificate.pdf")
                        val pdfWriter = PdfWriter.getInstance(document, outputStream)

                        class BackgroundColorEvent : PdfPageEventHelper() {
                            override fun onEndPage(writer: PdfWriter?, document: Document?) {
                                val contentByte = writer?.directContentUnder
                                contentByte?.saveState()
                                contentByte?.setRGBColorFill(245, 235, 213)
                                contentByte?.rectangle(0f, 0f, document?.pageSize?.width ?: 0f, document?.pageSize?.height ?: 0f)
                                contentByte?.fill()
                                contentByte?.restoreState()
                            }
                        }
                        pdfWriter.pageEvent = BackgroundColorEvent()

                        document.open()

                        val logo = context?.resources?.getIdentifier(
                            "file_base_image",
                            "drawable",
                            requireContext().packageName
                        )
                        val bitmap = logo?.let {
                            BitmapFactory.decodeResource(
                                context?.resources,
                                it
                            )
                        }
                        val byteArrayOutputStream = ByteArrayOutputStream()
                        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                        val imageBytes = byteArrayOutputStream.toByteArray()

                        val image = Image.getInstance(imageBytes)

                        image.alignment = Image.ALIGN_CENTER
                        image.scaleToFit(100f, 100f)

                        document.add(image)

                        val orgNameFont = Font(Font.FontFamily.HELVETICA, 16f, Font.BOLD, BaseColor.BLACK)
                        val titleFont = Font(Font.FontFamily.HELVETICA, 40f, Font.BOLDITALIC, BaseColor.BLACK)
                        val donationTitleFont = Font(Font.FontFamily.HELVETICA, 40f, Font.ITALIC, BaseColor.BLACK)
                        val paragraphFont = Font(Font.FontFamily.HELVETICA, 20f, Font.NORMAL, BaseColor.DARK_GRAY)
                        val usernameFont = Font(Font.FontFamily.HELVETICA, 22f, Font.BOLDITALIC, BaseColor.BLACK)
                        val ceonameFont = Font(Font.FontFamily.HELVETICA, 20f, Font.BOLDITALIC, BaseColor.BLACK)


                        val organizationName = "Wildcare"
                        val orgName = Paragraph(organizationName, orgNameFont)
                        orgName.alignment = Element.ALIGN_CENTER
                        document.add(orgName)

                        val certificateTitle = "Certificate of Appreciation"
                        val title = Paragraph(certificateTitle, titleFont)
                        title.alignment = Element.ALIGN_CENTER
                        document.add(title)

                        val appreciationMessage = "\nThis certificate acknowledge that \na symbolic adoption from\n"
                        val subTitle = Paragraph(appreciationMessage, paragraphFont)
                        subTitle.alignment = Element.ALIGN_CENTER
                        document.add(subTitle)

                        val username = "Xiang 1226\n"
                        val userName = Paragraph(username, usernameFont)
                        userName.alignment = Element.ALIGN_CENTER
                        document.add(userName)

                        val subtitle2 = "in our cause"
                        val subTitle2 = Paragraph(subtitle2, paragraphFont)
                        subTitle2.alignment = Element.ALIGN_CENTER
                        document.add(subTitle2)

                        val donationTitle = "Save Our Tiger\n"
                        val programs = Paragraph(donationTitle, donationTitleFont)
                        programs.alignment = Element.ALIGN_CENTER
                        document.add(programs)

                        val appreciationMessage2 = "\nhas been made with a generous\n" +
                                "contribution to World WildLife Fund\n" +
                                "to protect species and their habitats.\n\n\n"
                        val closingTitle = Paragraph(appreciationMessage2, paragraphFont)
                        closingTitle.alignment = Element.ALIGN_CENTER
                        document.add(closingTitle)

                        val signature = context?.resources?.getIdentifier(
                            "signature",
                            "drawable",
                            requireContext().packageName
                        )
                        val sigBitmap = signature?.let {
                            BitmapFactory.decodeResource(
                                context?.resources,
                                it
                            )
                        }
                        val sigByteArrayOutputStream = ByteArrayOutputStream()
                        sigBitmap?.compress(Bitmap.CompressFormat.PNG, 100, sigByteArrayOutputStream)
                        val sigImageBytes = sigByteArrayOutputStream.toByteArray()

                        val sigImage = Image.getInstance(sigImageBytes)

                        sigImage.alignment = Image.ALIGN_CENTER
                        sigImage.scaleToFit(100f, 100f)
                        document.add(sigImage)

                        val ceoNameText = "Lim Zhen Xiang"
                        val ceoName = Paragraph(ceoNameText, ceonameFont)
                        ceoName.alignment = Element.ALIGN_CENTER
                        document.add(ceoName)

                        val positionText = "President and CEO"
                        val position = Paragraph(positionText, paragraphFont)
                        position.alignment = Element.ALIGN_CENTER
                        document.add(position)

                        document.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        outputStream.close()
                    }
                }
            }
        }
    }

    fun checkPermissions(): Boolean {
        var writeSotragePermission = context?.let {
            ContextCompat.checkSelfPermission(
                it.applicationContext,
                WRITE_EXTERNAL_STORAGE
            )
        }

        var readStoragePermission = context?.let {
            ContextCompat.checkSelfPermission(
                it.applicationContext,
                READ_EXTERNAL_STORAGE
            )
        }

        return writeSotragePermission == PackageManager.PERMISSION_DENIED
                && readStoragePermission == PackageManager.PERMISSION_DENIED
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), permissionCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomBar()
    }
}