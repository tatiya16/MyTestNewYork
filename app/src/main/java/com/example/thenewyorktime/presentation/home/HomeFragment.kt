package com.example.thenewyorktime.presentation.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thenewyorktime.R
import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.databinding.HomeFragmentBinding
import com.example.thenewyorktime.datasource.api.exception.ApiException
import com.example.thenewyorktime.extension.getMessage
import com.example.thenewyorktime.extension.hideLoading
import com.example.thenewyorktime.extension.showLoading
import com.example.thenewyorktime.presentation.home.adapter.HomeAdapter
import com.example.thenewyorktime.presentation.home.adapter.OnItemHomeSelectedListener
import com.example.thenewyorktime.presentation.home.model.HomeUIModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnItemHomeSelectedListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserve()
        viewModel.fetchBooks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.homeFragment) {
            HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        }
        return NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun setupObserve() {
        viewModel.homeUIModelLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    homeAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    val message = it.throwable?.getMessage()
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    homeAdapter.submitList(it.data)
                }
                else -> {
                    showLoading()
                }
            }
        })
    }

    private fun setupView() {
        homeAdapter = HomeAdapter(this)
        binding.rcvHome.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = homeAdapter
        }
    }

    override fun onSelected(position: Int, homeUIModel: HomeUIModel) {
        val bookDetailUIModel = viewModel.mapToBookDetailUIModel(homeUIModel)
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(bookDetailUIModel)
        findNavController().navigate(action)
    }
}