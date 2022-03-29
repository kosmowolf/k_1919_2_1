package com.example.k_1919_2_1.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.ViewModel.MainViewModel
import com.example.k_1919_2_1.databinding.ActivityMainBinding
import com.example.k_1919_2_1.databinding.FragmentMainBinding

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

        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOne.setOnClickListener { }
        //view.findViewById<Button>(R.id.btnOne).setOnClickListener { }

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object:Observer<Any>{
            override fun onChanged(data: Any) {
                renderDate(data)
            }
        }
        //Подписался как слушатель
        viewModel.getData().observe(viewLifecycleOwner,observer)
        viewModel.getWeather()

    }

    private fun renderDate(data:Any){
        Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFragment()
    }
}