package com.example.k_1919_2_1.view.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.ViewModel.MainViewModel

import com.example.k_1919_2_1.databinding.FragmentWeatherListBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.example.k_1919_2_1.view.MainActivity
import com.example.k_1919_2_1.view.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WeatherListFragment : Fragment(),OnItemListClickListener {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
        get(){
            return _binding!!
        }

    val adapter = WeatherListAdapter(this)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWeatherListBinding.inflate(inflater,container,false)
        return binding.root
    }

    var isRussian = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RecyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object:Observer<AppState>{
            override fun onChanged(data: AppState) {
                renderDate(data)
            }
        }
        //Подписался как слушатель
        viewModel.getData().observe(viewLifecycleOwner,observer)
        binding.FloatingActionButton.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                viewModel.getWeatherRussia()
                binding.FloatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_russia))
            }else{
                binding.FloatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_earth))
                viewModel.getWeatherWorld()
            }
        }
        viewModel.getWeatherRussia()
    }

    private fun renderDate(data:AppState){
        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root,"Не получилось ${data.error}",Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)

//                binding.cityName.text = data.weatherData.city.name.toString()
//                binding.temperatureValue.text = data.weatherData.temperature.toString()
//                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
//                binding.cityCoordinates.text = "${data.weatherData.city.lat} ${data.weatherData.city.lon}"
//                Snackbar.make(binding.mainView,"Получилось",Snackbar.LENGTH_LONG).show()
                //Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =  WeatherListFragment()
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_BUNDLE_WEATHER,weather)
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container
            , DetailsFragment.newInstance(bundle)
        ).addToBackStack("").commit()
    }
}




