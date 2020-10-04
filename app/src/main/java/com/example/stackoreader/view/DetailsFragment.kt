package com.example.stackoreader.view

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.stackoreader.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(args.item.link)
        binding.webview.settings.javaScriptEnabled = true

        return binding.root
    }
}