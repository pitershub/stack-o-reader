package com.pikejprod.stackoreader.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.pikejprod.stackoreader.StackOReaderApp
import com.pikejprod.stackoreader.databinding.FragmentSearchBinding
import com.pikejprod.stackoreader.util.hideKeyboard
import com.pikejprod.stackoreader.viewmodel.SearchViewModel
import timber.log.Timber
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var resultListAdapter: ResultListAdapter

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as StackOReaderApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        resultListAdapter = ResultListAdapter(arrayListOf())
        Timber.d("Fragment init")

        binding.searchButton.setOnClickListener {
            startSearch()
            hideKeyboard()
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
            binding.swipeContainer.isRefreshing = false
        }

        viewModel.getLoadDataError().observe(viewLifecycleOwner) {
            if (it) {
                binding.resultsList.visibility = View.GONE
                binding.loadProgress.visibility = View.GONE
                binding.loadErrorText.visibility = View.VISIBLE
                binding.swipeContainer.isRefreshing = false
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

        binding.searchEdit.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.searchButton.isEnabled = binding.searchEdit.text.isNotEmpty()
                }
            })
    }

    private fun startSearch() {
        val query = binding.searchEdit.text.toString()
        if (query.isNotEmpty()) viewModel.search(query)
        else binding.swipeContainer.isRefreshing = false
    }

}