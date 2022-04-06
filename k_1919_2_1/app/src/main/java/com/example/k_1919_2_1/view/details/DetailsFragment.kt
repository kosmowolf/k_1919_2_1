package com.example.k_1919_2_1.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.databinding.FragmentDetailsBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather:Weather = requireArguments().getParcelable<Weather>(KEY_BUNDLE_WEATHER)!!
        renderDate(weather)
    }

    private fun renderDate(weather: Weather){
        binding.loadingLayout.visibility = View.GONE
        binding.cityName.text = weather.city.name.toString()
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
        Snackbar.make(binding.mainView,"Получилось",Snackbar.LENGTH_LONG).show()
        //Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()

    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

private fun Any.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<AppState>) {

}



