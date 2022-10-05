package com.example.navgraph03_10

import android.Manifest
import android.R.attr.defaultValue
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navgraph03_10.databinding.FragmentMeoreBinding
import kotlin.properties.Delegates


class MeoreFragment : Fragment() {

    lateinit var binding: FragmentMeoreBinding
    private lateinit var email: String
    lateinit var userName : String
    private val capturePhotoRequestCode = 1
    private val choosePhotoRequestCode = 2
    private lateinit var surati: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = FragmentMeoreBinding.inflate(inflater, container, false)
        val args: MeoreFragmentArgs = MeoreFragmentArgs.fromBundle(requireArguments())

        userName = args.username
        email =  args.email
        // Inflate the layout for this fragment
        val view = binding.root

        binding.tvSecondFragment1.text = email
        binding.tvSecondFragment2.text = userName
        binding.tvSecondFragment3.text = "Hello, welcome $userName"

        binding.takePhoto.setOnClickListener {
            requestPermission()
            capturePhoto()
        }

        binding.chooseFromGallery.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it,choosePhotoRequestCode)
            }

        }

        binding.btnSubmit.setOnClickListener {
            val action = MeoreFragmentDirections.actionMeoreFragmentToMesameFragment(email = email, username = userName, image = surati)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

    private fun hasCameraPermission () =
        ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED


    private fun requestPermission () {
        val permissionToRequest = mutableListOf<String>()

        if (!hasCameraPermission())
        {
            permissionToRequest.add(Manifest.permission.CAMERA)
        }
        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(requireActivity(), permissionToRequest.toTypedArray(),capturePhotoRequestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == capturePhotoRequestCode)
        {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireActivity(), "We have camera excess",
                        Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(requireActivity(), "We need camera excess",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, capturePhotoRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, photo: Intent?) {
        super.onActivityResult(requestCode, resultCode, photo)

        if (photo != null && resultCode == Activity.RESULT_OK)
        {
            surati = photo.data.toString().toUri()

            if (requestCode == capturePhotoRequestCode)
            {
                binding.profilePicture.setImageBitmap(photo.extras?.get("data") as Bitmap)
            }
            if (requestCode == choosePhotoRequestCode)
            {
                binding.profilePicture.setImageURI(surati)

            }
        }

    }

}