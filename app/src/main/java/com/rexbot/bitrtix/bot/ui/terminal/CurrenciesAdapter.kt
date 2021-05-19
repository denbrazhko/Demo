package com.rexbot.bitrtix.bot.ui.terminal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rexbot.bitrtix.bot.databinding.FragmentAccountBinding
import com.rexbot.bitrtix.bot.databinding.ItemCurrencyBinding
import com.rexbot.bitrtix.bot.network.models.CurrencyModel

class CurrenciesAdapter(
) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    private var _binding: ItemCurrencyBinding? = null
    private val binding get() = _binding!!


    var onCurrencyClickListener: OnCurrencyClickListener? = null

    var currencies: List<CurrencyModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = currencies[position]
        holder.tvFullName.text = currency.longName
        holder.tvShortName.text = currency.shortName
    }

    override fun getItemCount(): Int = currencies.size


    inner class ViewHolder(binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvShortName = binding.tvShortName
        val tvFullName = binding.tvFullName

        init {
            binding.root.setOnClickListener { onCurrencyClickListener?.onClick(adapterPosition) }
        }
    }

    interface OnCurrencyClickListener {
        fun onClick(id: Int)
    }
}