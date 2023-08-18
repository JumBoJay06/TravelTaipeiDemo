package com.jumbojay.traveltaipeidemo.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jumbojay.traveltaipeidemo.R
import com.jumbojay.traveltaipeidemo.databinding.ActivityMainBinding
import com.jumbojay.traveltaipeidemo.ui.attractionsdetail.AttractionsDetailFragment
import com.jumbojay.traveltaipeidemo.ui.attractionslist.AttractionsListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AttractionViewModel by viewModel()

    private lateinit var attractionsListFragment: AttractionsListFragment
    private lateinit var attractionsDetailFragment: AttractionsDetailFragment

    private var currentFragment: Fragment? = null
    private val fm = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (fm.fragments.isEmpty()) {
            initFragment()

            fm.beginTransaction()
                .add(binding.mainView.id, attractionsListFragment, "list_view")
                .add(binding.mainView.id, attractionsDetailFragment, "detail_view")
                .hide(attractionsDetailFragment)
                .commit()
        } else {
            attractionsListFragment = fm.findFragmentByTag("list_view") as AttractionsListFragment
            attractionsDetailFragment =
                fm.findFragmentByTag("detail_view") as AttractionsDetailFragment
        }
        currentFragment = attractionsListFragment

        viewModel.selectView.observe(this) {
            currentFragment = when (it) {
                ViewType.LIST -> {
                    fm.beginTransaction().hide(currentFragment!!).show(attractionsListFragment)
                        .commit()
                    attractionsListFragment
                }

                ViewType.DETAIL -> {
                    fm.beginTransaction().hide(currentFragment!!).show(attractionsDetailFragment)
                        .commit()
                    attractionsDetailFragment
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            when (currentFragment) {
                is AttractionsListFragment -> finish()
                is AttractionsDetailFragment -> viewModel.gotoList()
            }
        }
    }

    private fun initFragment() {
        attractionsListFragment = AttractionsListFragment()
        attractionsDetailFragment = AttractionsDetailFragment()
    }
}