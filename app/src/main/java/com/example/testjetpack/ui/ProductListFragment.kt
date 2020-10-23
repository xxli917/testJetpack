package com.example.testjetpack.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testjetpack.R
import com.example.testjetpack.SimpleArchitectureActivity
import com.example.testjetpack.adapter.ProductAdapter
import com.example.testjetpack.databinding.FragmentListFragmentBinding
import com.example.testjetpack.model.Product
import com.example.testjetpack.viewmodle.MyCompent
import com.example.testjetpack.viewmodle.ProductListViewModel

class ProductListFragment : Fragment() {
    companion object {
        val TAG = "ProductListViewModel"

    }

    private lateinit var binding: FragmentListFragmentBinding
    private lateinit var mProductAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myCompent = MyCompent()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_fragment, container,false,myCompent)


        mProductAdapter = ProductAdapter(productClickCallback)
        binding.productsList.adapter = mProductAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val productListMode = ViewModelProvider(this).get(ProductListViewModel::class.java)
        subscribeUi(productListMode)

    }

    private fun subscribeUi(viewModel: ProductListViewModel) {
        viewModel.getProducts()?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.isLoading = false
                mProductAdapter.setProductList(it)
            } else {
                binding.isLoading =true
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            binding.executePendingBindings()

        })

    }


    private val productClickCallback: ProductClickCallback = ProductClickCallback {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as SimpleArchitectureActivity).show(it)
        }
    }
}