package com.sarabyeet.flowerspedia.ui.fragments

import androidx.fragment.app.Fragment
import com.sarabyeet.flowerspedia.ui.MainActivity

abstract class BaseFragment: Fragment() {
    protected val activityViewModel
        get() = (activity as MainActivity).viewModel
}