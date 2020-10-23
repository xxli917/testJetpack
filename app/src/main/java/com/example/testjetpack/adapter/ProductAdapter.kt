package com.example.testjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testjetpack.R
import com.example.testjetpack.databinding.ProductItemBinding
import com.example.testjetpack.model.Product
import com.example.testjetpack.ui.ProductClickCallback
import androidx.recyclerview.widget.DiffUtil.Callback as Callback

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    lateinit var mProductClickCallback: ProductClickCallback
    constructor(productClickCallback: ProductClickCallback) : super() {
       mProductClickCallback = productClickCallback;

    }

    var mProductList: List<Product?> = ArrayList()

    fun setProductList(productList: List<Product?>) = if (mProductList == null) {
        mProductList = productList
        notifyItemRangeInserted(0, productList.size)
    } else {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object :Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return mProductList[oldItemPosition]?.id ===
                        productList[newItemPosition]!!.id            }

            override fun getOldListSize(): Int {
                return mProductList.size;
            }

            override fun getNewListSize(): Int {
                return productList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {


                val newProduct = productList[newItemPosition]
                val oldProduct = mProductList[oldItemPosition]
                return (newProduct!!.id === oldProduct?.id && newProduct!!.description == oldProduct.description
                        && newProduct.name == oldProduct.name
                        && newProduct.price === oldProduct.price)

            }


        })
        mProductList = productList
        result.dispatchUpdatesTo(this)
    }


    companion object internal
    class ProductViewHolder(binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: ProductItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ProductItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.product_item,
                parent, false
            )
        binding.setCallback(mProductClickCallback)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (mProductList == null) 0 else mProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product = mProductList[position]
        holder.binding.executePendingBindings()
    }

}