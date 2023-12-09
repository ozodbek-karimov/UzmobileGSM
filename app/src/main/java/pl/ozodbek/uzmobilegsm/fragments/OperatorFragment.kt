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
import pl.ozodbek.uzmobilegsm.databinding.FragmentOperatorBinding

class OperatorFragment : Fragment() {

    private var _binding: FragmentOperatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var operatorAdapter: OperatorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOperatorBinding.inflate(inflater, container, false)

        binding.upButton.setOnClickListener {
            findNavController().navigate(R.id.action_operatorFragment_to_homeFragment)
            (requireActivity() as MainActivity).binding.bottomnav.selectedItemId = R.id.action_add
        }


        operatorAdapter = OperatorAdapter(getOperatorDataList()) { operatorData ->
            val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$operatorData.phoneNumber"))

            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CODE_CALL_PHONE
                )
                return@OperatorAdapter
            }

            startActivity(dialIntent)
        }

        binding.recyclerview.adapter = operatorAdapter

        return binding.root
    }

    private fun getOperatorDataList(): List<OperatorData> {
        return listOf(
            OperatorData("Andijon", "+998-78-100-90-90"),
            OperatorData("Samarqand", "+998-7,8-100-90-90"),
            OperatorData("Farg'ona", "+998-78-100-40-90"),
            OperatorData("Urganch", "+998-78-100-30-90"),
            OperatorData("Nukus", "+998-78-100-20-90"),
            OperatorData("Namangan", "+998-78-180-90-90"),
            OperatorData("Buxoro", "+998-78-100-20-90"),
            OperatorData("Toshkent", "+998-78-101-90-90")
        ) + listOf(
            OperatorData("Surxondaryo", "+998-78-100-90-90"),
            OperatorData("Samarqand2", "+998-7,8-100-90-90"),
            OperatorData("Farg'ona3", "+998-78-100-40-90"),
            OperatorData("Urganch2", "+998-78-100-30-90"),
            OperatorData("Nukus3", "+998-78-100-20-90")
        )
    }

    companion object {
        private const val REQUEST_CODE_CALL_PHONE = 1
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}