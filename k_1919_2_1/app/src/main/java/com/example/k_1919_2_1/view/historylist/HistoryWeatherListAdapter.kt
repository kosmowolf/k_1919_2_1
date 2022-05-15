package com.example.k_1919_2_1.view.weatherList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.k_1919_2_1.databinding.FragmentHistoryWeatherListBinding
import com.example.k_1919_2_1.databinding.FragmentHistoryWeatherListRecyclerItemBinding
import com.example.k_1919_2_1.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_1919_2_1.repository.Weather

class HistoryWeatherListAdapter(
    private var data: List<Weather> = listOf()
) :
    RecyclerView.Adapter<HistoryWeatherListAdapter.CityHolder>() {

    fun setData(dataNew: List<Weather>) {
        this.data = dataNew
        notifyDataSetChanged() //DiffUtil кому интересно
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentHistoryWeatherListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size

    inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            FragmentHistoryWeatherListRecyclerItemBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                tvTemperature.text = weather.temperature.toString()
                tvFeelsLike.text = weather.feelsLike.toString()
                // HW вызвать отображение weather.icon
            }
        }
    }

}