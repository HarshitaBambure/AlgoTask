package com.harshita.algotask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harshita.algotask.databinding.ItemNumberBinding

class NumberAdapter :
    ListAdapter<AdapterModel, NumberAdapter.NumberViewHolder>(NumberDiffCallback()) {

    class NumberViewHolder(val binding: ItemNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(number: AdapterModel) {
            binding.tvNumber.text = number.number.toString()
            if (number.isHighlights) {
                binding.tvNumber.setBackgroundColor(Color.BLUE)  // Highlighted color
            } else {
                binding.tvNumber.setBackgroundColor(Color.GRAY)  // Default color
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NumberDiffCallback : DiffUtil.ItemCallback<AdapterModel>() {
    override fun areItemsTheSame(oldItem: AdapterModel, newItem: AdapterModel): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: AdapterModel, newItem: AdapterModel): Boolean {
        return oldItem.isHighlights == newItem.isHighlights
    }

}