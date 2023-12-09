package pl.ozodbek.uzmobilegsm.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import pl.ozodbek.uzmobilegsm.MainActivity
import pl.ozodbek.uzmobilegsm.R
import pl.ozodbek.uzmobilegsm.databinding.FragmentSozlamalarBinding
import java.util.Locale

class SozlamalarFragment : Fragment() {

    private var _binding: FragmentSozlamalarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSozlamalarBinding.inflate(inflater, container, false)

        binding.upButton.setOnClickListener {
            findNavController().navigate(R.id.action_sozlamalarFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add
        }

        binding.logOut.setOnClickListener {
            requireActivity().finish()
        }

        binding.uzbekLanguage.setOnClickListener {
            setLanguage(requireContext(), "uz")
            findNavController().navigate(R.id.action_sozlamalarFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add

        }

        binding.russsianLanguage.setOnClickListener {
            setLanguage(requireContext(), "ru")
            findNavController().navigate(R.id.action_sozlamalarFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add

        }

        binding.krillLanguage.setOnClickListener {
            setLanguage(requireContext(), "gb")
            findNavController().navigate(R.id.action_sozlamalarFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add

        }

        return binding.root
    }

    private fun setLanguage(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}