package com.example.thenewyorktime.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.thenewyorktime.R
import com.example.thenewyorktime.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var detailUIModel: BookDetailUIModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailUIModel = args.detailUIModel
        binding.itemTvTitle.text = detailUIModel?.title
        binding.itemTvAbstract.text = detailUIModel?.abstract
        Glide.with(requireContext())
            .load(detailUIModel?.urlImage)
            .fitCenter()
            .into(binding.imgBook)

        binding.btnOpenWeb.setOnClickListener {
            detailUIModel?.shortUrl?.let {
                val itn = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(itn)
            }

        }
    }
}