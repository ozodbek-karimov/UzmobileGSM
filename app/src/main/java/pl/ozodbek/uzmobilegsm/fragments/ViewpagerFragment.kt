package pl.ozodbek.uzmobilegsm.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.ozodbek.uzmobilegsm.MainActivity
import pl.ozodbek.uzmobilegsm.R
import pl.ozodbek.uzmobilegsm.databinding.FragmentViewpagerBinding

@SuppressLint("ResourceType")
class ViewpagerFragment : Fragment(R.layout.fragment_viewpager) {

    private var _binding: FragmentViewpagerBinding? =null
    private val binding get() = _binding!!
    private val args: ViewpagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewpagerBinding.inflate(inflater,container, false)

        binding.upButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewpagerFragment_to_homeFragment2)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add
        }


        binding.packageNames.text = args.dataList.package_name
        binding.packagePayment.text = args.dataList.package_payment
        binding.packageText.text = args.dataList.package_about


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}