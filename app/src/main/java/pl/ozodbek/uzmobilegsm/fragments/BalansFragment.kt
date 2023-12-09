package pl.ozodbek.uzmobilegsm.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.Manifest
import androidx.navigation.fragment.findNavController
import pl.ozodbek.uzmobilegsm.MainActivity
import pl.ozodbek.uzmobilegsm.R
import pl.ozodbek.uzmobilegsm.adapter.OperatorAdapter
import pl.ozodbek.uzmobilegsm.data.OperatorData
import pl.ozodbek.uzmobilegsm.databinding.FragmentBalansBinding
import pl.ozodbek.uzmobilegsm.databinding.FragmentOperatorBinding

class BalansFragment : Fragment() {

    private var _binding: FragmentBalansBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBalansBinding.inflate(inflater, container, false)

        binding.upButton.setOnClickListener {
            findNavController().navigate(R.id.action_balansFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add
        }

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}