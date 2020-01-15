package com.example.testjetpack.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testjetpack.MyGardenFragment
import com.example.testjetpack.PlantListFragment
import java.lang.IndexOutOfBoundsException

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class SunflowerPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    private var tabFragmentCreates: Map<Int,() -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { MyGardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() })
    override fun getItemCount(): Int {
        return tabFragmentCreates.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreates[position]?.invoke()?: throw IndexOutOfBoundsException()
    }

}