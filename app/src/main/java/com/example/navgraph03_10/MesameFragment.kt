package com.example.navgraph03_10

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navgraph03_10.databinding.FragmentMesameBinding

class MesameFragment : Fragment() {

    lateinit var binding: FragmentMesameBinding
    private lateinit var email: String
    private lateinit var username: String
    private lateinit var profilePicture: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMesameBinding.inflate(inflater, container, false)
        val args: MesameFragmentArgs = MesameFragmentArgs.fromBundle(requireArguments())

        email = args.email
        profilePicture = args.image

        binding.view1.text = email
        binding.profilePicture.setImageURI(profilePicture)
        binding.view2.text = "${binding.view2.text}, Welcome aboard ${args.username}"

        val view = binding.root


        return view
    }

}