package com.example.k_1919_2_1.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.k_1919_2_1.ViewModel.DetailsState
import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.databinding.FragmentDetailsBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.net.URL


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
                    /*Glide.with(requireContext())
                        .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                        .into(headerIcon)*/
                   /* Picasso.get()?.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                        ?.into(headerIcon)*/
                    headerCityIcon.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                    icon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")

                }
            }
        }

        //Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
    }

    fun ImageView.loadSvg(url: String){
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()
        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
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




