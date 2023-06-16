package com.example.capstoneduasatu.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.capstoneduasatu.MainActivity
import com.example.capstoneduasatu.databinding.FragmentUploadBinding
import com.example.capstoneduasatu.helper.reduceFileImage
import com.example.capstoneduasatu.helper.rotateImage
import com.example.capstoneduasatu.helper.uriToFile
import com.example.capstoneduasatu.viewmodel.UploadViewModel
import com.example.capstoneduasatu.viewmodel.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class UploadFragment : Fragment() {
    private lateinit var binding: FragmentUploadBinding
    private lateinit var launcherIntentGallery: ActivityResultLauncher<Intent>
    private var getFile: File? = null
    private val uploadViewModel: UploadViewModel by viewModels { ViewModelFactory.getInstance(requireContext()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launcherIntentGallery = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImg: Uri = result.data?.data as Uri
                val myFile = uriToFile(selectedImg, requireContext())

                getFile = myFile
                binding.ivImage.setImageURI(selectedImg)
            }
        }

        setupAction()
        setPermission()

    }
    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"

        val chooser = Intent.createChooser(intent, "Choose the Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun uploadArticle() {
        uploadViewModel.getSession().observe(viewLifecycleOwner) {
            if (getFile != null) {
                val file = reduceFileImage(getFile as File)
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestImageFile
                )
                uploadResponse(
                    it.token,
                    imageMultipart,
                    binding.tvJudul.text.toString().toRequestBody("text/plain".toMediaType()),
                    binding.tvDcs.text.toString().toRequestBody("text/plain".toMediaType()),
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Add an Image!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun uploadResponse(
        token: String,
        file: MultipartBody.Part,
        name:RequestBody,
        description: RequestBody
    ) {
        uploadViewModel.uploadArticle(token, file, name , description)
        uploadViewModel.uploadResponse.observe(this) {
            if (!it.error) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            btnGallery.setOnClickListener { openGallery() }
            btnUpload.setOnClickListener { uploadArticle() }
        }
    }

    private fun setPermission() {
        if (!allPermissionsGranted()) {
            requestPermissions(
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun allPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}