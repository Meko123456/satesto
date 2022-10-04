package com.example.navgraph03_10

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navgraph03_10.databinding.FragmentPirveliBinding

class PirveliFragment : Fragment() {

    lateinit var binding: FragmentPirveliBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPirveliBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.btnSubmit.setOnClickListener {
            val email = binding.emailView.text.toString()
            val password = binding.passwordView.text.toString()
            val repeatedPassword = binding.repeatPasswordView.text.toString()
            val userName = binding.userNameView.text.toString()



            if (isEmailCorrect(email) && isPasswordCorrect(password)
                && password == repeatedPassword && userName.isNotEmpty())
            {
                val bundle = Bundle()


                bundle.putString("email", "ragacc meili")
                bundle.putInt("password", 56)

                val meoreFragmenti = MeoreFragment()
                meoreFragmenti.arguments = bundle



                val action = PirveliFragmentDirections.actionPirveliFragmentToMeoreFragment()
            Navigation.findNavController(view).navigate(action)
            }else
            {
                Toast.makeText(requireActivity(), "Your input password, username or email in not correct",
                    Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }

    private fun isEmailCorrect (mail: String): Boolean
    {
        return mail.contains('@') && mail.contains('.')
                && mail.length > 5
    }
    private fun isPasswordCorrect (password: String): Boolean
    {
        return  password.isNotEmpty() && password.length >= 8 && password.count { it.isDigit() } >= 2
    }
}