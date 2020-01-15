package com.example.testjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.testjetpack.adapter.GardenPlantingAdapter
import com.example.testjetpack.adapter.PLANT_LIST_PAGE_INDEX
import com.example.testjetpack.databinding.MyGardenFragmentBinding
import com.example.testjetpack.utilities.InjectorUtils
import com.example.testjetpack.viewmodle.MyGardenViewModel
import kotlin.reflect.KProperty


class MyGardenFragment : Fragment() {

    companion object {
        fun newInstance() = MyGardenFragment()
    }

    private lateinit var binding: MyGardenFragmentBinding

    private val viewModel: MyGardenViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyGardenFragmentBinding.inflate(inflater,container,false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter

        binding.addPlant.setOnClickListener {
            navigateToPlantListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: MyGardenFragmentBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }
}


