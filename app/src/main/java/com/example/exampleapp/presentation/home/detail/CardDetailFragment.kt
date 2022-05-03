package com.example.exampleapp.presentation.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.exampleapp.R
import com.example.exampleapp.databinding.FragmentDetailsBinding
import com.example.exampleapp.model.Cards
import com.example.exampleapp.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CardDetailFragment : Fragment() {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.selectedCard.value?.let { item ->
            fillDetails(item)
        }
    }

    private fun fillDetails(item: Cards) {
        binding.cardDetailSubtitle.text =
            getString(R.string.detail_head_pattern, item.type, item.number, item.power, item.rarity)
        binding.cardDetailDescription.text = item.text

        Glide.with(binding.cardDetailImage)
            .load(item.imageUrl)
            .placeholder(R.drawable.no_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.cardDetailImage)

        updateTitle(item.name)
    }

    // Update activity title
    private fun updateTitle(title: String) {
        if (requireActivity() is AppCompatActivity) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = title
        }
    }

}