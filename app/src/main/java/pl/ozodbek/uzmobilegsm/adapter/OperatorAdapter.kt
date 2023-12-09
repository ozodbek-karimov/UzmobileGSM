package pl.ozodbek.uzmobilegsm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ozodbek.uzmobilegsm.data.OperatorData
import pl.ozodbek.uzmobilegsm.databinding.OperatorsRowItemBinding

class OperatorAdapter(
    private val list: List<OperatorData>,
    val onItemClick: (OperatorData) -> Unit
) : RecyclerView.Adapter<OperatorAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: OperatorsRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(operatorData: OperatorData) {
            binding.apply {
                regionTv.text = operatorData.region
                phoneNumberTv.text = operatorData.phoneNumber

                callButton.setOnClickListener { onItemClick.invoke(operatorData) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(
          OperatorsRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}