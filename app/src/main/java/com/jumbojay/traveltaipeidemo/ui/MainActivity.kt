package com.jumbojay.traveltaipeidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jumbojay.traveltaipeidemo.R
import com.jumbojay.traveltaipeidemo.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AttractionViewModel by viewModel()

    private lateinit var attractionsListFragment: AttractionsListFragment
    private lateinit var attractionsDetailFragment: AttractionsDetailFragment
    private lateinit var webFragment: WebFragment

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
                .add(binding.mainView.id, webFragment, "web_view")
                .hide(webFragment)
                .commit()
        } else {
            attractionsListFragment = fm.findFragmentByTag("list_view") as AttractionsListFragment
            attractionsDetailFragment = fm.findFragmentByTag("detail_view") as AttractionsDetailFragment
            webFragment = fm.findFragmentByTag("web_view") as WebFragment
        }
        currentFragment = attractionsListFragment

     onBackPressedDispatcher.addCallback(this) {
         // todo back pressed
     }
    }

    private fun initFragment() {
        attractionsListFragment = AttractionsListFragment()
        attractionsDetailFragment = AttractionsDetailFragment()
        webFragment = WebFragment()
    }
}