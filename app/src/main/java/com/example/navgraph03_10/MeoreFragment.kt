package com.example.navgraph03_10

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navgraph03_10.databinding.FragmentMeoreBinding
import kotlin.properties.Delegates


class MeoreFragment : Fragment() {

    lateinit var binding: FragmentMeoreBinding
    private lateinit var email: String
    private var password by Delegates.notNull<Int>()
    lateinit var userName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        binding = FragmentMeoreBinding.inflate(inflater, container, false)


        // Inflate the layout for this fragment
        val view = binding.root

        val bundle = this.arguments
        if (bundle != null) {
            email = bundle.getString("email").toString()
            password = bundle.getInt("password")
            userName = bundle.getString("username").toString()
        }
        binding.tvSecondFragment1.text = email
        binding.tvSecondFragment2.text = password.toString()
        binding.tvSecondFragment3.text = userName

        binding.tvSecondFragment3.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_meoreFragment_to_pirveliFragment)
        }

        return view
    }

}