package com.example.stackoreader.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.stackoreader.R
import com.example.stackoreader.databinding.SearchFragmentBinding
import com.example.stackoreader.viewmodel.SearchViewModel
import timber.log.Timber

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        Timber.d("Fragment init")

        binding.searchButton.setOnClickListener {
            val query = binding.searchEdit.text.toString()
            viewModel.search(query)
        }
    }

}