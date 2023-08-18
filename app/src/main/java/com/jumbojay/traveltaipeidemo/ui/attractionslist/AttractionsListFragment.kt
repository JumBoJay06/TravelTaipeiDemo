package com.jumbojay.traveltaipeidemo.ui.attractionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumbojay.traveltaipeidemo.data.Language
import com.jumbojay.traveltaipeidemo.databinding.ListViewBinding
import com.jumbojay.traveltaipeidemo.ui.AttractionViewModel
import com.jumbojay.traveltaipeidemo.util.DialogUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import timber.log.Timber

class AttractionsListFragment : Fragment() {
    private val viewModel: AttractionViewModel by activityViewModel()

    private lateinit var binding: ListViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val attractionsAdapter = AttractionsAdapter()
        binding.listView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = attractionsAdapter
        }

        binding.swipeRefresh.setOnRefreshListener {
            attractionsAdapter.refresh()
        }

        lifecycleScope.launch {
            attractionsAdapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> binding.swipeRefresh.isRefreshing = true
                    else -> binding.swipeRefresh.isRefreshing = false
                }
            }

            viewModel.attractionsPagingFlow
                .collectLatest { pagingData ->
                    pagingData.map {
                        Timber.e("${it.images} : ${it.name}")
                    }
                    attractionsAdapter.submitData(pagingData)
                }
        }

        attractionsAdapter.onClick = {
            viewModel.onClickItem(it)
        }

        viewModel.selectLang.observe(viewLifecycleOwner) {
            attractionsAdapter.refresh()
        }

        binding.translateBtn.setOnClickListener {
            DialogUtil.createPickDialog(
                requireContext(),
                "選擇語言",
                Language.values(),
                Language.values().indexOf(viewModel.selectLang.value ?: Language.ZH_TW),
                "確認",
                "取消",
                { language -> language.languageName },
                { language, _ ->
                    viewModel.setLang(language)
                }
            ).show(requireActivity().supportFragmentManager, "dialog")
        }
    }
}