package com.example.k_1919_2_1.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k_1919_2_1.ViewModel.DetailsState
import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.databinding.FragmentDetailsBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment(){

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

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, object: Observer<DetailsState> {
            override fun onChanged(t: DetailsState) {
                renderDate(t)
            }
        })
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            viewModel.getWeather(it.city)
        }
    }



    private fun renderDate(detailsState: DetailsState){
        when(detailsState){
            is DetailsState.Error -> {
                //TODO()
            }
            DetailsState.Loading -> {
                //TODO()
            }
            is DetailsState.Success -> {
                val weather = detailsState.weather
                with(binding){
                    loadingLayout.visibility = View.GONE
                    cityName.text = weather.city.name

                        temperatureValue.text = weather.temperature.toString()
                        feelsLikeValue.text = weather.feelsLike.toString()
                        cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"

                    Snackbar.make(mainView,"Получилось",Snackbar.LENGTH_LONG).show()

                }
            }
        }

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




