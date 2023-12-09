package pl.ozodbek.uzmobilegsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import pl.ozodbek.uzmobilegsm.databinding.FragmentInternetSpeedBinding

class InternetSpeedFragment : Fragment() {

    private var _binding: FragmentInternetSpeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInternetSpeedBinding.inflate(inflater, container, false)

        binding.webview.webViewClient = object : WebViewClient() {}
        binding.webview.loadUrl("https://fast.com/")

        binding.upButton.setOnClickListener{
            findNavController().navigate(R.id.action_internetSpeedFragment_to_homeFragment)
        }

        binding.refresh.setOnClickListener {
            binding.webview.reload()
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webview.canGoBack()) {
                    binding.webview.goBack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}