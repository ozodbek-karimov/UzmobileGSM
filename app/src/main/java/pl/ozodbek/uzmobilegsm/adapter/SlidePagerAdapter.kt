package pl.ozodbek.uzmobilegsm.adapter

import ExampleData
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class SlidePagerAdapter(
    fragment: Fragment,
    private val slideFragments: List<Fragment>,
    private val onItemClick: (position: Int) -> Unit

) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = slideFragments.size

    override fun createFragment(position: Int): Fragment = slideFragments[position]

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
        }
    }

}
