package com.example.tbc_course_18.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tbc_course_18.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {


    private val args: InfoFragmentArgs by navArgs()
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        binding.apply {
            Glide.with(this@InfoFragment).load(args.cover).into(appCompatImageView)
            title.text = args.title
            content.text = args.description
            publish.text = args.publishDate
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}