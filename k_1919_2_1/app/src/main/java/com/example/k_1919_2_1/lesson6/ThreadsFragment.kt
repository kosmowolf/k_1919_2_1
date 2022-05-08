package com.example.k_1919_2_1.lesson6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import com.example.k_1919_2_1.databinding.FragmentThreadsBinding
import kotlinx.android.synthetic.main.fragment_threads.*
import java.lang.Thread.sleep


class ThreadsFragment : Fragment(){

    private var _binding: FragmentThreadsBinding? = null
    private val binding: FragmentThreadsBinding
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
    ): View {
        _binding = FragmentThreadsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            button1.setOnClickListener{
                Thread{
                    val time = editText.text.toString().toLong()
                    sleep(time*1000L)
                    requireActivity().runOnUiThread{textView1.text = "$time сек. 1 "}
                    Handler(Looper.getMainLooper()).post{textView1.text = "$time сек. 2"}
                }.start()

            }
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() =  ThreadsFragment()
    }




}






