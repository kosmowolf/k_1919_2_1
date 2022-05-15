package com.example.k_1919_2_1.view.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.ViewModel.HistoryViewModel
import com.example.k_1919_2_1.ViewModel.MainViewModel
import com.example.k_1919_2_1.databinding.FragmentHistoryWeatherListBinding

import com.example.k_1919_2_1.databinding.FragmentWeatherListBinding
import com.example.k_1919_2_1.repository.Weather
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.example.k_1919_2_1.view.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar

class HistoryWeatherListFragment : Fragment() {

    private var _binding: FragmentHistoryWeatherListBinding? = null
    private val binding: FragmentHistoryWeatherListBinding
        get(){
            return _binding!!
        }

    private val adapter = HistoryWeatherListAdapter()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHistoryWeatherListBinding.inflate(inflater,container,false)
        return binding.root
    }

    var isRussian = true
   // val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
   private val viewModel:HistoryViewModel by lazy {
       ViewModelProvider(this).get(HistoryViewModel::class.java)
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.also{//TODO HW Вынести в initRecycler
            it.adapter = this@HistoryWeatherListFragment.adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        val observer = {data: AppState->renderDate(data)}
        //Подписался как слушатель
        viewModel.getData().observe(viewLifecycleOwner,observer)

        viewModel.getAll()
    }



    private fun renderDate(data:AppState){
        when(data){
            is AppState.Error -> {
               // binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root,"Не получилось ${data.error}",Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
               // binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
               // binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)

            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =  WeatherListFragment()
    }




}




