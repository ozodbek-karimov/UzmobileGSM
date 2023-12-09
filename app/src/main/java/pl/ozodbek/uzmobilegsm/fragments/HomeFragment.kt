package pl.ozodbek.uzmobilegsm.fragments

import ExampleData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pl.ozodbek.uzmobilegsm.R
import pl.ozodbek.uzmobilegsm.ViewPagerItem
import pl.ozodbek.uzmobilegsm.ViewPagerItem2
import pl.ozodbek.uzmobilegsm.ViewPagerItem3
import pl.ozodbek.uzmobilegsm.ViewPagerItem4
import pl.ozodbek.uzmobilegsm.adapter.SlidePagerAdapter
import pl.ozodbek.uzmobilegsm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val DELAY_MS: Long = 3000 // delay in milliseconds before auto sliding starts
    private val PERIOD_MS: Long = 4500 // time in milliseconds between sliding items


    private var slideHandler: Handler? = null
    private var slideRunnable: Runnable? = null
    private lateinit var slidePagerAdapter: SlidePagerAdapter

    private val slideFragments = listOf(
        ViewPagerItem(),
        ViewPagerItem2(),
        ViewPagerItem3(),
        ViewPagerItem4()
    )

    private val dataList = listOf(
        ExampleData(
            "Tekin!!",
            "10000",
            "Tarif rejasi  jismoniy va yuridik shaxslar uchun, 2019-yil  1-fеvraldan 30-iyunga qadar taqdim etiladi." +
                    "Kiritilgan daqiqa limiti, MB, SMS 1 kalеndar oyiga taqdim etiladi." +
                    "Tarif rejasiga «O‘zbеktеlеkom» AK sotuvlar ofisi, shuningdek, dilеrlar orqali ulanish mumkin." +
                    "Abonеnt to‘lovini hisoblash abonеnt faol bo‘lganida amalga oshiriladi.  Agar abonеnt hisobida pul mablag‘i " +
                    "yetarli bo‘lmaganligi sababli abonеnt bloklangan bo‘lsa, to‘lov kiritilmaguniga qadar to‘lov hisoblanmaydi." +
                    " Ushbu tarif rejasiga ulanish yoki unga o‘tishda abonent to‘lovi kalendar oyidagi qolgan kunlar soniga nisbatan,  " +
                    "kiritilgan daqiqalar, SMS va Internet trafigi, shuningdek, qolgan kunlar soni bo‘yicha (to‘liq daqiqaga yaxlitlangan holda) hisoblab chiqiladi."
        ),
        ExampleData(
            "Another package",
            "5000",
            "Tarif rejasi  jismoniy va yuridik shaxslar uchun, 2019-yil  1-fеvraldan 30-iyunga qadar taqdim etiladi." +
                    "Kiritilgan daqiqa limiti, MB, SMS 1 kalеndar oyiga taqdim etiladi." +
                    "Tarif rejasiga «O‘zbеktеlеkom» AK sotuvlar ofisi, shuningdek, dilеrlar orqali ulanish mumkin." +
                    "Abonеnt to‘lovini hisoblash abonеnt faol bo‘lganida amalga oshiriladi.  Agar abonеnt hisobida pul mablag‘i " +
                    "yetarli bo‘lmaganligi sababli abonеnt bloklangan bo‘lsa, to‘lov kiritilmaguniga qadar to‘lov hisoblanmaydi." +
                    " Ushbu tarif rejasiga ulanish yoki unga o‘tishda abonent to‘lovi kalendar oyidagi qolgan kunlar soniga nisbatan,  " +
                    "kiritilgan daqiqalar, SMS va Internet trafigi, shuningdek, qolgan kunlar soni bo‘yicha (to‘liq daqiqaga yaxlitlangan holda) hisoblab chiqiladi."
        ),
        ExampleData(
            "Yet another package",
            "20000",
            "Tarif rejasi  jismoniy va yuridik shaxslar uchun, 2019-yil  1-fеvraldan 30-iyunga qadar taqdim etiladi." +
                    "Kiritilgan daqiqa limiti, MB, SMS 1 kalеndar oyiga taqdim etiladi." +
                    "Tarif rejasiga «O‘zbеktеlеkom» AK sotuvlar ofisi, shuningdek, dilеrlar orqali ulanish mumkin." +
                    "Abonеnt to‘lovini hisoblash abonеnt faol bo‘lganida amalga oshiriladi.  Agar abonеnt hisobida pul mablag‘i " +
                    "yetarli bo‘lmaganligi sababli abonеnt bloklangan bo‘lsa, to‘lov kiritilmaguniga qadar to‘lov hisoblanmaydi." +
                    " Ushbu tarif rejasiga ulanish yoki unga o‘tishda abonent to‘lovi kalendar oyidagi qolgan kunlar soniga nisbatan,  " +
                    "kiritilgan daqiqalar, SMS va Internet trafigi, shuningdek, qolgan kunlar soni bo‘yicha (to‘liq daqiqaga yaxlitlangan holda) hisoblab chiqiladi."
        ),
        ExampleData(
            "Last package",
            "8000",
            "Tarif rejasi  jismoniy va yuridik shaxslar uchun, 2019-yil  1-fеvraldan 30-iyunga qadar taqdim etiladi." +
                    "Kiritilgan daqiqa limiti, MB, SMS 1 kalеndar oyiga taqdim etiladi." +
                    "Tarif rejasiga «O‘zbеktеlеkom» AK sotuvlar ofisi, shuningdek, dilеrlar orqali ulanish mumkin." +
                    "Abonеnt to‘lovini hisoblash abonеnt faol bo‘lganida amalga oshiriladi.  Agar abonеnt hisobida pul mablag‘i " +
                    "yetarli bo‘lmaganligi sababli abonеnt bloklangan bo‘lsa, to‘lov kiritilmaguniga qadar to‘lov hisoblanmaydi." +
                    " Ushbu tarif rejasiga ulanish yoki unga o‘tishda abonent to‘lovi kalendar oyidagi qolgan kunlar soniga nisbatan,  " +
                    "kiritilgan daqiqalar, SMS va Internet trafigi, shuningdek, qolgan kunlar soni bo‘yicha (to‘liq daqiqaga yaxlitlangan holda) hisoblab chiqiladi."
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        slidePagerAdapter = SlidePagerAdapter(this, slideFragments) { dataLists ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToViewpagerFragment(dataList[dataLists])
            findNavController().navigate(action)
        }
        binding.viewPager.adapter = slidePagerAdapter


        binding.viewpagerIndicator.setViewPager(binding.viewPager)


        binding.telegramButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/UZMOBILE (https://t.me/UZMOBILE)")
                )
            )
        }

        binding.speedButton.setOnClickListener {
            findNavController().navigate(R.id.internetSpeedFragment)
        }

        binding.ussdButton.setOnClickListener{
            findNavController().navigate(R.id.ussdFragment)
        }
        binding.tarifButton.setOnClickListener{
            findNavController().navigate(R.id.tariflarFragment)
        }

        binding.xizmatButton.setOnClickListener{
            findNavController().navigate(R.id.xizmatlarFragment)
        }

        binding.minutButton.setOnClickListener{
            findNavController().navigate(R.id.minutFragment)
        }

        binding.internetButton.setOnClickListener{
            findNavController().navigate(R.id.internetPaketFragment)
        }
        binding.smsButton.setOnClickListener{
            findNavController().navigate(R.id.smsPaketFragment)
        }




        return binding.root
    }


    private fun startSlideHandler() {
        slideHandler = Handler(Looper.getMainLooper())
        slideRunnable = Runnable {
            binding.viewPager.currentItem =
                (binding.viewPager.currentItem + 1) % slideFragments.size
            slideRunnable?.let { slideHandler?.postDelayed(it, PERIOD_MS) }
        }
        slideHandler?.postDelayed(slideRunnable!!, DELAY_MS)
    }

    private fun stopSlideHandler() {
        slideRunnable?.let { slideHandler?.removeCallbacks(it) }
        slideHandler = null
    }


    override fun onResume() {
        super.onResume()
        startSlideHandler()
    }

    override fun onPause() {
        super.onPause()
        stopSlideHandler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
