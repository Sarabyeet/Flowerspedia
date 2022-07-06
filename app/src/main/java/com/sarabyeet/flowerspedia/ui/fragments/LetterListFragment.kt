package com.sarabyeet.flowerspedia.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarabyeet.flowerspedia.R
import com.sarabyeet.flowerspedia.databinding.FragmentLetterListBinding
import com.sarabyeet.flowerspedia.epoxy.EpoxyLetterController

class LetterListFragment: BaseFragment() {
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private var isGridMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.letterListRecyclerView.setControllerAndBuildModels(
            EpoxyLetterController { letterId ->
                activityViewModel.selectedFlowerLetterLiveData.postValue(letterId)
                findNavController().navigate(LetterListFragmentDirections.actionLetterListFragmentToWordListFragment())
        })
        isGridMode = binding.letterListRecyclerView.layoutManager is GridLayoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options_layout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.layoutViewOption -> {
                setUpLayoutManager()
                item.setIcon(setIcon(isGridMode))
                true
            }
            else -> true
        }
    }
    private fun setUpLayoutManager(){
        if (isGridMode){
            binding.letterListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.letterListRecyclerView.layoutManager = GridLayoutManager(requireContext(),4)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}