package com.example.exampleapp.presentation.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleapp.R
import com.example.exampleapp.databinding.FragmentListBinding
import com.example.exampleapp.model.Cards
import com.example.exampleapp.presentation.common.model.ResourceState
import com.example.exampleapp.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CardListFragment : Fragment(), CardListAdapter.OnItemClickListener {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private var cardListAdapter: CardListAdapter? = null

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (homeViewModel.getCards().value == null)
            homeViewModel.fetchCards()

        homeViewModel.getCards().observe(viewLifecycleOwner) {
            handleCardsState(it)
        }

        initRecyclerView()
    }

    override fun onItemClick(item: Cards) {
        homeViewModel.selectedCard.value = item

        findNavController().navigate(R.id.navigate_cardListFragment_to_cardDetailFragment)
    }

    private fun handleCardsState(state: ResourceState<List<Cards>>) {
        when (state.status) {
            ResourceState.Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ResourceState.Status.SUCCESS -> {
                binding.progressBar.visibility = View.GONE

                state.data?.let { items ->
                    cardListAdapter?.fillList(items)
                }
            }
            ResourceState.Status.ERROR -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        cardListAdapter = CardListAdapter(emptyList())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = cardListAdapter

        cardListAdapter?.setOnItemClickListener(this)
    }

}