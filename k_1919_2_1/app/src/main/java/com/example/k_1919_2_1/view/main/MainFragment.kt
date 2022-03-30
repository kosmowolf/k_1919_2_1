package com.example.k_1919_2_1.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.*
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.ViewModel.MainViewModel
import com.example.k_1919_2_1.databinding.ActivityMainBinding
import com.example.k_1919_2_1.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding //утечка памяти

    override fun onDestroy() {
        super.onDestroy()
       // binding=null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object:Observer<AppState>{
            override fun onChanged(data: AppState) {
                renderDate(data)
            }
        }
        //Подписался как слушатель
        viewModel.getData().observe(viewLifecycleOwner,observer)
        viewModel.getWeather()
    }

    private fun renderDate(data:AppState){
        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView,"Не получилось ${data.error}",Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.cityName.text = data.weatherData.city.name.toString()
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                binding.cityCoordinates.text = "${data.weatherData.city.lat} ${data.weatherData.city.lon}"
                Snackbar.make(binding.mainView,"Получилось",Snackbar.LENGTH_LONG).show()
                //Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFragment()
    }
}

private fun Any.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<AppState>) {

}



