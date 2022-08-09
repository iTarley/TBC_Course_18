package com.example.tbc_course_18.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_course_18.R
import com.example.tbc_course_18.adapter.CustomAdapter
import com.example.tbc_course_18.databinding.FragmentMainBinding
import com.example.tbc_course_18.viewModel.MainViewModel
import com.example.tbc_course_18.viewModel.MainViewModel.Resource
import com.google.android.material.snackbar.Snackbar
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
                    when (it){

                        is Resource.Success -> {
                            adapter.setData(it.data)
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                        is Resource.Error -> {
                            Log.d("ErrorResponse", it.message)
                            Snackbar.make(view,getString(R.string.error),Snackbar.LENGTH_LONG).show()
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }

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

    class LinearLayoutManagerWrapper : LinearLayoutManager {
        constructor(context: Context?) : super(context) {}
        constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
            context,
            orientation,
            reverseLayout
        ) {
        }

        constructor(
            context: Context?,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int
        ) : super(context, attrs, defStyleAttr, defStyleRes) {
        }

        override fun supportsPredictiveItemAnimations(): Boolean {
            return false
        }
    }




}