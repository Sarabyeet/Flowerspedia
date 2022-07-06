package com.sarabyeet.flowerspedia.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarabyeet.flowerspedia.R
import com.sarabyeet.flowerspedia.databinding.FragmentWordListBinding
import com.sarabyeet.flowerspedia.epoxy.EpoxyWordController

class WordListFragment: BaseFragment() {
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    private var isGridMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        activityViewModel.flowersListLiveData.observe(viewLifecycleOwner){ flowersList ->
            activityViewModel.selectedFlowerLetterLiveData.observe(viewLifecycleOwner){ letterId ->
                val wordController = EpoxyWordController(flowersList,letterId, requireContext())
                wordController.onClickCallBack = { flower ->
//                    (activity as MainActivity).openMapsIntent(flower)
                    findNavController().navigate(WordListFragmentDirections.actionWordListFragmentToFlowerWebViewFragment(flower))
                }
                binding.wordListRecyclerView.setControllerAndBuildModels(wordController)
            }
        }
        isGridMode = binding.wordListRecyclerView.layoutManager is GridLayoutManager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.layoutViewOption -> {
                setUpLayoutManager()
                item.setIcon(setIcon(isGridMode))
                true
            }
            android.R.id.home-> {
                requireActivity().onBackPressed()
                true
            }
            else -> true
        }
    }

    private fun setUpLayoutManager(){
        if (isGridMode){
            binding.wordListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.wordListRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }
        isGridMode = !isGridMode
    }

    private fun setIcon(isGrid:Boolean): Int{
        return if (isGrid){
            R.drawable.ic_round_format_list_bulleted_24
        } else {
            R.drawable.ic_round_grid_view_24
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options_layout, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}