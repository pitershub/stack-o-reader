package com.pikejprod.stackoreader.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.pikejprod.stackoreader.StackOReaderApp
import com.pikejprod.stackoreader.databinding.FragmentSearchBinding
import com.pikejprod.stackoreader.util.hideKeyboard
import com.pikejprod.stackoreader.viewmodel.SearchViewModel
import com.pikejprod.stackoreader.viewmodel.StackOAction
import com.pikejprod.stackoreader.viewmodel.StackOState
import com.ww.roxie.BaseViewModel
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


        viewModel.observableState.observe(this, Observer {state ->
            state?.let {
                renderState(it)
            }
        })
    }

    private fun renderState(state: StackOState) {
            binding.loadProgress.visibility = if (state.activity) View.VISIBLE else View.GONE
            binding.loadErrorText.visibility = if (state.displayError) View.VISIBLE else View.GONE
            binding.resultsList.visibility = if (!state.activity && !state.displayError) View.VISIBLE else View.GONE
            resultListAdapter.updateResultsList(state.resultList)
            binding.swipeContainer.isRefreshing = false

    }

    private fun startSearch() {
        val query = binding.searchEdit.text.toString()
        if (query.isNotEmpty()) viewModel.dispatch(StackOAction.SearchButtonClicked(query))
        else binding.swipeContainer.isRefreshing = false
    }

}