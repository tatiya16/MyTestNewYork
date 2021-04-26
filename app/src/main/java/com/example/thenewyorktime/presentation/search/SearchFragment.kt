package com.example.thenewyorktime.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.databinding.SearchFragmentBinding
import com.example.thenewyorktime.extension.getMessage
import com.example.thenewyorktime.extension.hideLoading
import com.example.thenewyorktime.extension.showLoading
import com.example.thenewyorktime.presentation.search.adapter.OnItemSearchSelectedListener
import com.example.thenewyorktime.presentation.search.adapter.SearchAdapter
import com.example.thenewyorktime.presentation.search.model.SearchBookUIModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), OnItemSearchSelectedListener {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var searchAdapter: SearchAdapter

    private var jobTextWatcher: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserve()
    }

    private fun setupObserve() {
        searchViewModel.searchBookLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    searchAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.throwable?.getMessage(), Toast.LENGTH_SHORT)
                        .show()
                    searchAdapter.submitList(it.data)
                }
                else -> showLoading()
            }
        })
    }

    private fun setupView() {
        searchAdapter = SearchAdapter(this)
        binding.rcvSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.searchingBook(binding.edtSearch.text.toString())
        binding.edtSearch.addTextChangedListener(textChangeListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobTextWatcher?.cancel()
        binding.edtSearch.removeTextChangedListener(textChangeListener)
    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            jobTextWatcher?.cancel()
            jobTextWatcher = lifecycleScope.launch {
                delay(500)
                searchViewModel.searchingBook(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onSelected(position: Int, searchBookUIModel: SearchBookUIModel) {
        val detailUIModel = searchViewModel.mapToDetailUIModel(searchBookUIModel)
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(detailUIModel)
        findNavController().navigate(action)
    }
}