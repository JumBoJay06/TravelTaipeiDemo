package com.jumbojay.traveltaipeidemo.ui.attractionsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.jumbojay.traveltaipeidemo.databinding.DeatilViewBinding
import com.jumbojay.traveltaipeidemo.ui.AttractionViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AttractionsDetailFragment : Fragment() {
    private val viewModel: AttractionViewModel by activityViewModel()

    private lateinit var binding: DeatilViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DeatilViewBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imagePagerAdapter = ImagePagerAdapter()
        binding.photosPager.adapter = imagePagerAdapter
        viewModel.selectAttractions.observe(viewLifecycleOwner) {
            binding.photosPager.currentItem = 0
            it?.let { attractions ->
                binding.toolBar.title = attractions.name
                binding.name.text = attractions.name
                binding.introduction.text = attractions.introduction
                binding.address.text = "Address\n${attractions.address}"
                binding.modified.text = "Last Updated Time\n${attractions.modified}"
                binding.officialSite.text = attractions.officialSite
                imagePagerAdapter.setData(attractions.images)
            } ?: run {
                binding.toolBar.title = ""
                imagePagerAdapter.setData(emptyList())
            }
        }

        TabLayoutMediator(binding.dot, binding.photosPager) { tab, _ ->
            tab.view.isClickable = false
        }.attach()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == android.R.id.home) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    return false
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}