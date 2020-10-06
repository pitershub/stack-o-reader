package com.example.stackoreader.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.stackoreader.R
import com.example.stackoreader.databinding.FragmentSearchBinding
import com.example.stackoreader.viewmodel.SearchViewModel
import timber.log.Timber

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var resultListAdapter: ResultListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        resultListAdapter = ResultListAdapter(arrayListOf())
        Timber.d("Fragment init")

        binding.searchButton.setOnClickListener {
            startSearch()
        }

        binding.resultsList.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = resultListAdapter
        }

        viewModel.getResultsList().observe(
            viewLifecycleOwner
        ) { list ->
            binding.loadProgress.visibility = View.GONE
            binding.loadErrorText.visibility = View.GONE
            binding.resultsList.visibility = View.VISIBLE
            resultListAdapter.updateResultsList(list)
            binding.swipeContainer.let {
                if (it.isRefreshing) it.isRefreshing = false
            }

        }

        viewModel.getLoadDataError().observe(viewLifecycleOwner) {
            if (it) {
                binding.resultsList.visibility = View.GONE
                binding.loadProgress.visibility = View.GONE
                binding.loadErrorText.visibility = View.VISIBLE
            }
        }

        viewModel.getLoadInProgress().observe(viewLifecycleOwner) {
            if (it) {
                binding.resultsList.visibility = View.GONE
                binding.loadErrorText.visibility = View.GONE
                binding.loadProgress.visibility = View.VISIBLE
            }
        }

        binding.swipeContainer.setOnRefreshListener {
            startSearch()
        }
    }

    private fun startSearch() {
        val query = binding.searchEdit.text.toString()
        if (query.isNotEmpty()) viewModel.search(query)
    }

}