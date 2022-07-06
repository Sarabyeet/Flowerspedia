package com.sarabyeet.flowerspedia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.sarabyeet.flowerspedia.databinding.FragmentFlowerWebViewBinding

class FlowerWebViewFragment : BaseFragment() {
    private var _binding: FragmentFlowerWebViewBinding? = null
    private val binding get() = _binding!!

    private val flowerNameArgs: FlowerWebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFlowerWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.google.com/search?q=${flowerNameArgs.flowerName}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


