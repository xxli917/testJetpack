package com.example.testjetpack

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.testjetpack.adapter.PlantAdapter
import com.example.testjetpack.databinding.PlantListFragmentBinding
import com.example.testjetpack.utilities.InjectorUtils
import com.example.testjetpack.viewmodle.PlantListViewModel


class PlantListFragment : Fragment() {

    companion object {
        fun newInstance() = PlantListFragment()
    }

    //var 属性需要实现 getValue() setValue() 函数,val 只是需要getValue() 即可。两函数都需要用 operator 关键字来进行标记。
    //android kotlin Lazy viewModle no methed set value thus it canot server as a delegate for val
    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantListFragmentBinding.inflate(inflater,container,false)
        context ?: return binding.root
        val adapter = PlantAdapter()
        binding.plantList.adapter= PlantAdapter()
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            Log.i("---","--"+viewModel.plants.value?.size)
            adapter.submitList(plants)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
}
