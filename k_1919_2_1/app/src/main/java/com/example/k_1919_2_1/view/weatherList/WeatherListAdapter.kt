package com.example.k_1919_2_1.view.weatherList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.databinding.FragmentWeatherListBinding
import com.example.k_1919_2_1.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.example.k_1919_2_1.view.MainActivity
import com.example.k_1919_2_1.view.details.DetailsFragment

class WeatherListAdapter(private val onItemListClickListener: OnItemListClickListener,
                         private var data: List<Weather> = listOf()
    ): RecyclerView.Adapter<WeatherListAdapter.CityHolder>() {

    fun setData(dataNew: List<Weather>){
        this.data = dataNew
        notifyDataSetChanged() //DiffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size

    inner class CityHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(weather: Weather){
            FragmentWeatherListRecyclerItemBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                root.setOnClickListener{
                    onItemListClickListener.onItemClick(weather)
                }
            }

        }
    }
}