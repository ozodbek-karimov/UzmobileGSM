package pl.ozodbek.uzmobilegsm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import pl.ozodbek.uzmobilegsm.R

/**
 * A simple [Fragment] subclass.
 * Use the [XizmatlarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class XizmatlarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xizmatlar, container, false)
    }

}