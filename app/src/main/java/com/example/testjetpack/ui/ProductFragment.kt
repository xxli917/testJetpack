package com.example.testjetpack.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testjetpack.R
import com.example.testjetpack.adapter.CommentAdapter
import com.example.testjetpack.databinding.FragmentListFragmentBinding
import com.example.testjetpack.viewmodle.MyCompent
import com.example.testjetpack.viewmodle.ProductViewModel

class ProductFragment : Fragment() {
    companion object{
        fun forProduct(productId: Int): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putInt(ProductFragment.KEY_PRODUCT_ID, productId)
            fragment.arguments = args
            return fragment        }

        val TAG = "ProductViewModel"
        private const val KEY_PRODUCT_ID = "product_id"


    }

    private lateinit var binding: FragmentListFragmentBinding
    private lateinit var mCommentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myCompent = MyCompent()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false,myCompent)

        mCommentAdapter = CommentAdapter(commentClickCallback)
        binding.productsList.adapter = mCommentAdapter

        return binding.root
    }

    private val commentClickCallback: CommentClickCallback = CommentClickCallback {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var productViewMode = ViewModelProvider(this).get(ProductViewModel::class.java)
        subscribeUi(productViewMode)

    }

    //@BindingAdapter("app:visibleGone")

    private fun subscribeUi(productViewMode: ProductViewModel) {
            productViewMode.getObservableProduct().observe(viewLifecycleOwner, Observer {
                productViewMode.setProduct(it)

            })

        // Observe comments
        productViewMode.getComments()
            .observe(viewLifecycleOwner, Observer {

                if (it != null) {
                    binding.isLoading = false
                    mCommentAdapter.setCommentList(it)
                } else {
                    binding.isLoading = true
                }
            })

    }
}