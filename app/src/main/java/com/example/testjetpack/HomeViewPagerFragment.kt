package com.example.testjetpack

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.testjetpack.adapter.MY_GARDEN_PAGE_INDEX
import com.example.testjetpack.adapter.PLANT_LIST_PAGE_INDEX
import com.example.testjetpack.adapter.SunflowerPagerAdapter
import com.example.testjetpack.databinding.HomeViewPagerFragmentBinding
import com.example.testjetpack.viewmodle.HomeViewPagerViewModel
import com.google.android.material.tabs.TabLayoutMediator


class HomeViewPagerFragment : Fragment() {

    companion object {
        fun newInstance() = HomeViewPagerFragment()
    }

    private lateinit var binding: HomeViewPagerFragmentBinding
    private lateinit var viewModel: HomeViewPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeViewPagerFragmentBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SunflowerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> null
        }
    }
}
