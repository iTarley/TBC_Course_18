package com.example.tbc_course_18.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_course_18.adapter.CustomAdapter
import com.example.tbc_course_18.databinding.FragmentMainBinding
import com.example.tbc_course_18.viewModel.MainViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        CustomAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        navigate()
        viewModel.getContent()
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.apiResponse.collect{
                    adapter.setData(it)
                }
            }
        }
    }

    private fun initRecycler(){
        binding.mainRecycler.adapter = adapter
        adapter.submitList(emptyList())

    }

    private fun navigate(){
        adapter.onClick = {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToInfoFragment(
                    it.descriptionKA, it.publish_date, it.titleKA, it.cover
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}