package com.jumbojay.traveltaipeidemo.ui

import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WebFragment : Fragment() {
    private val viewModel: AttractionViewModel by activityViewModel()
}